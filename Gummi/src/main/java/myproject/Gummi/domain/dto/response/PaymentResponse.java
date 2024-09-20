package myproject.Gummi.domain.dto.response;

import lombok.Getter;
import myproject.Gummi.domain.entity.PaymentCategory;
import java.time.LocalDate;


@Getter
public class PaymentResponse {

    private long id;
    private long price;
    private PaymentCategory paymentCategory;
    private String reason;
    private boolean isSettled;
    private LocalDate payDate;

    public PaymentResponse() {
    }

    public PaymentResponse(long id, long price, PaymentCategory paymentCategory, String reason, boolean isSettled, LocalDate payDate) {
        this.id = id;
        this.price = price;
        this.paymentCategory = paymentCategory;
        this.reason = reason;
        this.isSettled = isSettled;
        this.payDate = payDate;
    }
}
