package myproject.Gummi.api.controller;

import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.service.ImageService;
import myproject.Gummi.domain.dto.response.ImageDetailResponse;
import myproject.Gummi.domain.dto.response.ImageResponse;
import myproject.Gummi.domain.entity.Image;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // 사진 등록
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> saveImage(@RequestPart("image") MultipartFile image){
        Image newImg = imageService.upload(image);
        return new ResponseEntity<>(newImg, HttpStatus.OK);
    }

    // 전체 조회(Shooting Time 기준)
    @GetMapping
    public ResponseEntity<List<ImageResponse>> getImages(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        List<Image> images = imageService.getImages(page, size);
        List<ImageResponse> response = images.stream()
                .map(ImageResponse::of)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 특정 사진 조회
    @GetMapping("/{imageId}")
    public ResponseEntity<ImageDetailResponse> getImage(@PathVariable("imageId") Long imageId){
        // 요청,응답객체는 컨트롤러 레이어에서만 알고있도로 하자, 서비스 레이어에는 도메인 객체를 넘기도록 하자
        ImageDetailResponse response = ImageDetailResponse.of(imageService.getImage(imageId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 특정 사진 삭제
    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable("imageId") Long imageId){
        imageService.deleteImage(imageId);
        return ResponseEntity.ok("삭제에 성공하였습니다.");
    }

}
