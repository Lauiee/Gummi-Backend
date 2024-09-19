package myproject.Gummi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imageUrl;
    private LocalDate shootingDate;
    private LocalDate createdAt;
    private boolean deletedAt;

    public Image() {
    }

    public static Image of(String imageUrl, LocalDate shootingTime){
        Image image = new Image();
        image.setImageUrl(imageUrl);
        image.setShootingDate(shootingTime);
        image.setCreatedAt(LocalDate.now()); // 현재 날짜로 설정
        image.setDeletedAt(false); // 기본값으로 false 설정
        return image;
    }


}
