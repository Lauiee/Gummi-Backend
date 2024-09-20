package myproject.Gummi.domain.dto.response;

import lombok.Getter;
import lombok.Setter;
import myproject.Gummi.domain.entity.Payment;
import myproject.Gummi.domain.entity.PaymentCategory;

import java.time.LocalDate;

@Setter
@Getter
public class PaymentDetailResponse {

    private long id;
    private long price;
    private PaymentCategory paymentCategory;
    private String reason;
    private boolean isSettled;
    private LocalDate payDate;
    private LocalDate createdAt;

    public PaymentDetailResponse(long id, long price, PaymentCategory paymentCategory, String reason, boolean isSettled, LocalDate payDate, LocalDate createdAt) {
        this.id = id;
        this.price = price;
        this.paymentCategory = paymentCategory;
        this.reason = reason;
        this.isSettled = isSettled;
        this.payDate = payDate;
        this.createdAt = createdAt;
    }

    public static PaymentDetailResponse of(Payment payment){
        return new PaymentDetailResponse(
                payment.getId(),
                payment.getPrice(),
                payment.getPaymentCategory(),
                payment.getReason(),
                payment.isSettled(),
                payment.getPayDate(),
                payment.getCreatedAt()
        );
    }
}
