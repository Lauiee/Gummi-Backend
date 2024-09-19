package myproject.Gummi.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import myproject.Gummi.global.exception.ErrorCode;
import myproject.Gummi.global.exception.S3Exception;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3ImageUploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public String uploadImage(MultipartFile image) {
        try {
            return uploadToS3(image);
        } catch (IOException e) {
            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_UPLOAD);
        }
    }

    private String uploadToS3(MultipartFile image) throws IOException {
        // 원본 파일의 이름을 추출해서 uuid가 추가된 새로운 이름을 지어준다
        // 같이 추출한 확장자는 메타데이터에 사용된다
        String origianlFilename = image.getOriginalFilename(); // 원본 파일 명
        String extention = origianlFilename.substring(origianlFilename.lastIndexOf(".")); // 확장자
        // 랜덤 생성된 UUID의 앞 10자 + 원본파일 이름
        String s3FileName = UUID.randomUUID().toString().substring(0,10) + origianlFilename; // 변경된 파일 명

        // S3로 넘기기 위해서 파일 내용을 입력 스트림에서 바이트 배열로 변환
        InputStream is = image.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);

        // 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/" + extention);
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        // S3에 파일 업로드
        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, s3FileName, byteArrayInputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest); // put image to S3
        } catch (Exception e) {
            throw new S3Exception(ErrorCode.PUT_OBJECT_EXCEPTION);
        } finally {
            // 스트림들을 닫아 자원 누수 방지
            byteArrayInputStream.close();
            is.close();
        }
        return amazonS3.getUrl(bucketName, s3FileName).toString();
    }
}
