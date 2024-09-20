package myproject.Gummi.api.controller;

import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.service.PaymentService;
import myproject.Gummi.domain.dto.request.PaymentSaveRequest;
import myproject.Gummi.domain.entity.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;
    // 지출 금액 업로드
    @PostMapping
    public ResponseEntity<Payment> savePayment(@RequestBody PaymentSaveRequest paymentSaveRequest){
        return new ResponseEntity<>(paymentService.save(Payment.fromDto(paymentSaveRequest)), HttpStatus.CREATED);
    }

    // 지출 금액 조회

    // 지출 금액 상세 조회
    // 정산 여부 변경
    // 지출 금액 삭제
}
