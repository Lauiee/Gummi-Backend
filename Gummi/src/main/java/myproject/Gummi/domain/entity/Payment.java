package myproject.Gummi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long price;
    private Category category;
    private String reason;
    private LocalDate payDate;
    private boolean isSettled;
    private LocalDate createdAt;
    private boolean isDeleted;

    public Payment() {
    }
}
