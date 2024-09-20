package myproject.Gummi.api.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.repository.PaymentRepository;
import myproject.Gummi.domain.dto.response.PaymentDetailResponse;
import myproject.Gummi.domain.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Payment payment = paymentRepository.findByIdAndNotDeleted(paymentId);
        if (payment == null) {
            throw new EntityNotFoundException("Payment not found or has been deleted.");
        }
         payment.setDeleted(true);
         paymentRepository.save(payment);
    }

    public List<Payment> getPayments(Boolean isSettled) {
        return paymentRepository.findByIsSettled(isSettled);
    }

    public Payment getPayment(long paymentId) {
        Payment payment = paymentRepository.findByIdAndNotDeleted(paymentId);
        if (payment == null) {
            throw new EntityNotFoundException("Payment not found or has been deleted.");
        }
        return payment;
    }

    public Payment updateSettled(long paymentId, boolean isSettled) {
        Payment payment = paymentRepository.findByIdAndNotDeleted(paymentId);
        if (payment == null) {
            throw new EntityNotFoundException("Payment not found or has been deleted.");
        }
        payment.setSettled(isSettled);
        return paymentRepository.save(payment);
    }
}
