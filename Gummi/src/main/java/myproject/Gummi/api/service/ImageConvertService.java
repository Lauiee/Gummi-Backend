package myproject.Gummi.api.service;

import myproject.Gummi.api.service.util.ByteArrayMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageConvertService {

    public MultipartFile convertToJpg(MultipartFile image) throws IOException {

        // 원래이름 가져오기
        String originalFilename = image.getOriginalFilename();
        String filenameWithoutExtension = getFilenameWithoutExtension(originalFilename);

        // Save MultipartFile to a temporary HEIC file
        File tempHeicFile = File.createTempFile("tempImage", ".heic");
        image.transferTo(tempHeicFile);

        // Define the output JPG file
        String outputPath = tempHeicFile.getAbsolutePath().replace(".heic", ".jpg");
        File outputJpgFile = new File(outputPath);

        // Create and configure ProcessBuilder for ImageMagick
        ProcessBuilder pb = new ProcessBuilder(
                "magick", "convert",
                tempHeicFile.getAbsolutePath(),
                outputJpgFile.getAbsolutePath()
        );
        pb.redirectErrorStream(true);

        // Execute the conversion
        Process process = pb.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new IOException("Error during image conversion", e);
        }

        // Convert the output JPG file to MultipartFile
        byte[] jpgBytes = Files.readAllBytes(outputJpgFile.toPath());
        MultipartFile convertedImage = new ByteArrayMultipartFile(jpgBytes, filenameWithoutExtension+".jpg", "image/jpeg");

        // Clean up temporary files
        tempHeicFile.delete();
        outputJpgFile.delete();

        return convertedImage;
    }

    private static String getFilenameWithoutExtension(String originalFilename) {

        if (originalFilename == null || originalFilename.isEmpty()) {
            return originalFilename;
        }

        int lastDotIndex = originalFilename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            // 확장자가 없는 경우, 전체 이름을 반환
            return originalFilename;
        }

        // 확장자를 제외한 파일 이름 반환
        return originalFilename.substring(0, lastDotIndex);
    }
}
