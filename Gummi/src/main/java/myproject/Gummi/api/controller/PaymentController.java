package myproject.Gummi.api.controller;

import lombok.RequiredArgsConstructor;
import myproject.Gummi.api.service.PaymentService;
import myproject.Gummi.domain.dto.request.PaymentSaveRequest;
import myproject.Gummi.domain.dto.response.PaymentResponse;
import myproject.Gummi.domain.entity.Payment;
import myproject.Gummi.domain.mapper.PaymentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getPayments(
            @RequestParam(value = "isSettled", required = false) Boolean isSettled){
        // 파라미터가 없으면 전체 조회
        List<PaymentResponse> responses = PaymentMapper.toResponseList(paymentService.getPayments(isSettled));
        return new ResponseEntity<>(responses,HttpStatus.OK);

    }

    // 지출 금액 상세 조회

    // 정산 여부 변경

    // 지출 금액 삭제
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable("paymentId") long paymentId){
        paymentService.delete(paymentId);
        return ResponseEntity.ok("지출 내역 삭제에 성공하였습니다.");
    }
}
