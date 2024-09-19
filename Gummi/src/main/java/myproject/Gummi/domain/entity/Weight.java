package myproject.Gummi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import myproject.Gummi.domain.dto.request.WeightSaveRequest;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double weight;
    private LocalDate calDate;
    private LocalDate createdAt;
    private boolean deletedAt;

    public Weight() {
    }

    public Weight(double weight, LocalDate calDate) {
        this.weight = weight;
        this.calDate = calDate;
        this.createdAt = LocalDate.now(); // 현재 날짜로 설정
        this.deletedAt = false;
    }

    public static Weight fromDto(WeightSaveRequest weightSaveRequest){
        return new Weight(
                weightSaveRequest.getWeight(),
                weightSaveRequest.getCalDate()
        );
    }
}
