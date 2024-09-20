package myproject.Gummi.domain.dto.request;

import lombok.Getter;
import myproject.Gummi.domain.entity.PaymentCategory;

import java.time.LocalDate;

@Getter
public class PaymentSaveRequest {

    private PaymentCategory paymentCategory;
    private long price;
    private String reason;
    private LocalDate payDate;
}
