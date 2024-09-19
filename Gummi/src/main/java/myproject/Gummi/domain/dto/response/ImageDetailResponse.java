package myproject.Gummi.domain.dto.response;


import lombok.Getter;
import myproject.Gummi.domain.entity.Image;

@Getter

public class ImageDetailResponse {

    private String imageUrl;

    public ImageDetailResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static ImageDetailResponse of(Image findimage){
        return new ImageDetailResponse(
                findimage.getImageUrl()
        );
    }
}
