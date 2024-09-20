package myproject.Gummi.domain.mapper;

import myproject.Gummi.domain.dto.response.PaymentResponse;
import myproject.Gummi.domain.entity.Payment;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapper {

    public static PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getPrice(),
                payment.getPaymentCategory(),
                payment.getReason(),
                payment.isSettled(),
                payment.getPayDate()
        );
    }

    public static List<PaymentResponse> toResponseList(List<Payment> payments) {
        return payments.stream()
                .map(PaymentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
