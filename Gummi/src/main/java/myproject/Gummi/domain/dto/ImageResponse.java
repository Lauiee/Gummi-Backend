package myproject.Gummi.domain.dto;

import lombok.Getter;
import lombok.Setter;
import myproject.Gummi.domain.entity.Image;

import java.time.LocalDate;

@Setter
@Getter
public class ImageResponse {

    private long id;
    private String imageUrl;
    private LocalDate shootingDate;
    private LocalDate createdAt;

    public static ImageResponse of(Image image) {
        ImageResponse response = new ImageResponse();
        response.setId(image.getId());
        response.setImageUrl(image.getImageUrl());
        response.setShootingDate(image.getShootingDate());
        response.setCreatedAt(image.getCreatedAt());
        return response;
    }
}
