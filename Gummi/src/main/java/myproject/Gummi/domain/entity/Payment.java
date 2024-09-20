package myproject.Gummi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import myproject.Gummi.domain.dto.request.PaymentSaveRequest;
import myproject.Gummi.domain.dto.request.WeightSaveRequest;

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
    private PaymentCategory paymentCategory;

    public Payment() {
    }

    public Payment(PaymentCategory paymentCategory, long price, String reason, LocalDate payDate) {
        this.paymentCategory = paymentCategory;
        this.payDate = payDate;
        this.reason = reason;
        this.price = price;
        this.createdAt = LocalDate.now();
        this.isSettled = false;
        this.isDeleted = false;
    }

    public static Payment fromDto(PaymentSaveRequest paymentSaveRequest){
        return new Payment(
                paymentSaveRequest.getPaymentCategory(),
                paymentSaveRequest.getPrice(),
                paymentSaveRequest.getReason(),
                paymentSaveRequest.getPayDate()
        );
    }
}
