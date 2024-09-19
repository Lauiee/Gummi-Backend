package myproject.Gummi.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ImageConvertServiceTest {
    private final ImageConvertService imageConvertService = new ImageConvertService();

    @Test
    void testConvertHeicToJpg() throws IOException {
        // HEIC 테스트 이미지 로드
        ClassPathResource resource = new ClassPathResource("testHEIC.HEIC");
        MultipartFile heicFile = new MockMultipartFile(
                "test-image.heic",
                resource.getFilename(),
                "image/heic",
                Files.readAllBytes(resource.getFile().toPath())
        );

        // HEIC 파일을 JPG로 변환
        MultipartFile jpgFile = imageConvertService.convertToJpg(heicFile);

        // 변환된 JPG 파일의 정보 검증
        assertNotNull(jpgFile);
        assertEquals("converted-image.jpg", jpgFile.getOriginalFilename());
        assertEquals("image/jpeg", jpgFile.getContentType());
        assertNotNull(jpgFile.getBytes());
    }
}