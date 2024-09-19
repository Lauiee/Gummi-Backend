package myproject.Gummi.api.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.repository.ImageRepository;
import myproject.Gummi.domain.entity.Image;
import myproject.Gummi.global.exception.ConvertException;
import myproject.Gummi.global.exception.ErrorCode;
import myproject.Gummi.global.exception.S3Exception;
import myproject.Gummi.global.exception.SoftDeletedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageConvertService imageConvertService;
    private final ImageMetadataService imageMetadataService;
    private final S3ImageUploadService s3ImageUploadService;
    private final ImageRepository imageRepository;

    public Image upload(MultipartFile image){
        // 입력받은 이미지 파일이 빈 파일인지 검증
        if(image.isEmpty() || Objects.isNull(image.getOriginalFilename())){
            throw new S3Exception(ErrorCode.EMPTY_FILE_EXCEPTION);
        }

        // 파일 확장자 검증(jpg/jpeg/heic)만 통과
        validateImageFileExtension(image.getOriginalFilename());

        // 메타데이터 추출
        LocalDate shootingTime;
        try {
            shootingTime = imageMetadataService.extractShootingDate(image);
        } catch (IOException e) {
            throw new S3Exception(ErrorCode.MISSING_SHOOTING_DATE);
        }

        // 이미지 변환
        MultipartFile convertedImage;
        String originalFilename = image.getOriginalFilename().toLowerCase();
        if (originalFilename.endsWith(".heic")) {
            try {
                convertedImage = imageConvertService.convertToJpg(image);
            } catch (IOException e) {
                throw new ConvertException(ErrorCode.FAILED_CONVERT_TO_JPG);
            }
        } else {
            convertedImage = image;
        }

        // uploadToS3를 호출하여 S3에 이미지 저장, 저장된 이미지의 URL 반환
        String imgUrl = s3ImageUploadService.uploadImage(convertedImage);

        // Image 객체 생성 및 저장
        return save(imgUrl, shootingTime);
    }

    // 파일 확장자 검증 메서드
    private void validateImageFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new S3Exception(ErrorCode.NO_FILE_EXTENTION);
        }

        String extention = filename.substring(lastDotIndex + 1).toLowerCase();
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "heic");

        if (!allowedExtentionList.contains(extention)) {
            throw new S3Exception(ErrorCode.INVALID_FILE_EXTENTION);
        }
    }

    // 이미지 객체 생성&저장 메서드
    @Transactional
    private Image save(String imgUrl, LocalDate shootingTime){
        Image image = Image.of(imgUrl, shootingTime);
        return imageRepository.save(image);
    }

    // 이미지 전체 조회
    public List<Image> getImages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("shootingDate").ascending());
        return imageRepository.findByDeletedAtFalseOrderByShootingDateAsc(pageable);
    }

    // 개별 이미지 조회
    public Image getImage(Long imageId){
        Image image = imageRepository.findById(imageId).orElseThrow(()
                -> new EntityNotFoundException("Image not found with id: " + imageId));
        if (image.isDeletedAt()) {
            throw new SoftDeletedException("삭제된 이미지입니다. imageId: " + imageId);
        }
        return image;
    }

    // 이미지 삭제
    @Transactional
    public void deleteImage(Long imageId){
        // soft delete이기에 실제로 삭제 X
        Image findImage = imageRepository.findById(imageId).orElseThrow(()
                -> new EntityNotFoundException("Image not found with id: " + imageId));

        // deleted_at만 true로 변경
        findImage.setDeletedAt(true);
        imageRepository.save(findImage);
    }


}
