package com.samir.payments.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageablePaymentResponse {
    private List<PaymentResponseDto> paymentResponseDto;
    private Long totalElements;
    private int totalPages;
    private boolean hasNextPage;
}
