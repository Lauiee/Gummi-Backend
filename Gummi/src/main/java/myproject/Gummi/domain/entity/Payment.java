package myproject.Gummi.domain.entity;

import jakarta.persistence.*;
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
    private String reason;
    private LocalDate payDate;
    private boolean isSettled;
    private LocalDate createdAt;
    private boolean isDeleted;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    public Payment() {
    }
}
