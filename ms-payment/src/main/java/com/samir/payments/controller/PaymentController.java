package com.samir.payments.controller;

import com.samir.payments.model.request.PaymentCriteria;
import com.samir.payments.model.request.PaymentRequestDto;
import com.samir.payments.model.response.PageablePaymentResponse;
import com.samir.payments.model.response.PaymentResponseDto;
import com.samir.payments.model.request.UpdatePaymentRequestDto;
import com.samir.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        paymentService.savePayment(paymentRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponseDto getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<PaymentResponseDto> getPayments(PaymentCriteria paymentCriteria) {
//        return paymentService.getPayments(paymentCriteria);
//    }

    // http://localhost:8000/v1/payments?page=0&count=10&amountFrom=125
    // The question mark (?) in a URL is used to indicate the beginning of the query string.
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageablePaymentResponse getPayments(@RequestParam int page,
                                               @RequestParam int count,
                                               PaymentCriteria paymentCriteria) {
        return paymentService.getPayments(page, count, paymentCriteria);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePayment(@PathVariable Long id, @RequestBody UpdatePaymentRequestDto updatePaymentRequestDto) {
        paymentService.updatePaymentById(id, updatePaymentRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
    }
}
