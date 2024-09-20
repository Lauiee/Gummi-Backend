package myproject.Gummi.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.repository.PaymentRepository;
import myproject.Gummi.domain.entity.Payment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
