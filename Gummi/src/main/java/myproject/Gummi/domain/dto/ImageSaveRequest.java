package myproject.Gummi.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ImageSaveRequest {

    private MultipartFile image;

}
