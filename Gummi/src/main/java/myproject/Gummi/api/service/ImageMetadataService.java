package myproject.Gummi.api.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class ImageMetadataService {

    // 메타데이터 추출 메서드
    public LocalDate extractShootingDate(MultipartFile image) throws IOException {
        try (InputStream imageInputStream = image.getInputStream()) {
            Metadata metadata = ImageMetadataReader.readMetadata(imageInputStream);

            // 우선적으로 EXIF 서브 IFD 디렉토리에서 촬영일자 추출
            ExifSubIFDDirectory subIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (subIFDDirectory != null) {
                Date date = subIFDDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                if (date != null) {
                    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }
            }

            // 기본 EXIF 디렉토리에서 촬영일자 추출
            ExifIFD0Directory ifd0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (ifd0Directory != null) {
                String dateStr = ifd0Directory.getString(ExifIFD0Directory.TAG_DATETIME);
                if (dateStr != null) {
                    // EXIF 날짜 문자열을 LocalDate로 변환
                    return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss"));
                }
            }
        } catch (Exception e) {
            throw new IOException("Error reading image metadata", e);
        }

        throw new IllegalStateException("Shooting date not found in image metadata");
    }
}
