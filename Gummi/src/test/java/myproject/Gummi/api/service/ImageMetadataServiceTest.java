package myproject.Gummi.api.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ImageMetadataServiceTest {

    @Autowired
    private ImageMetadataService imageMetadataService;

    // JPG 메타데이터 추출 테스트
    @Test
    void testExtractJpgImageShootingDate() throws IOException {
        // 테스트 이미지 로드
        ClassPathResource resource = new ClassPathResource("testJPG.JPG");
        MultipartFile mockImage = new MockMultipartFile("testJPG.JPG",
                resource.getFilename(),
                "image/jpeg",
                Files.readAllBytes(resource.getFile().toPath()));

        // 촬영일자 추출
        LocalDate shootingDate = imageMetadataService.extractShootingDate(mockImage);

        // 검증
        assertEquals(LocalDate.of(2010, 4, 29), shootingDate); // Use expected date
    }

    // HEIC 메타데이터 추출 테스트
    @Test
    void testExtractHeicImageShootingDate() throws IOException {
        // 테스트 이미지 로드
        ClassPathResource resource = new ClassPathResource("testHEIC.HEIC");
        MultipartFile mockImage = new MockMultipartFile("testHEIC.HEIC",
                resource.getFilename(),
                "image/heic",
                Files.readAllBytes(resource.getFile().toPath()));

        // 촬영일자 추출
        LocalDate shootingDate = imageMetadataService.extractShootingDate(mockImage);

        // 검증
        assertEquals(LocalDate.of(2024, 6, 12), shootingDate); // Use expected date
    }


}