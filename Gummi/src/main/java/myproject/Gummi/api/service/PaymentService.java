package myproject.Gummi.api.service;

import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public void delete(long paymentId) {
         Payment payment = paymentRepository.findById(paymentId).orElseThrow(()
                -> new EntityNotFoundException("payment not found with id: " + paymentId));
         payment.setDeleted(true);
         paymentRepository.save(payment);
    }
}
