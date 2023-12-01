package com.samir.payments.mapper;

import com.samir.payments.dao.entity.PaymentEntity;
import com.samir.payments.model.request.PaymentRequestDto;
import com.samir.payments.model.response.PaymentResponseDto;
import com.samir.payments.model.request.UpdatePaymentRequestDto;

import java.time.LocalDateTime;

public class PaymentMapper {
    public static PaymentEntity mapRequestToEntity(PaymentRequestDto paymentRequestDto) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount(paymentRequestDto.getAmount());
        paymentEntity.setDescription(paymentRequestDto.getDescription());
        return paymentEntity;
    }

    public static PaymentResponseDto mapEntityToResponse(PaymentEntity paymentEntity) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setId(paymentEntity.getId());
        paymentResponseDto.setAmount(paymentEntity.getAmount());
        paymentResponseDto.setDescription(paymentEntity.getDescription());
        paymentResponseDto.setResponseAt(LocalDateTime.now());

        return paymentResponseDto;
    }

    public static void updatePaymentById(UpdatePaymentRequestDto updatePaymentRequestDto, PaymentEntity paymentEntity) {
        paymentEntity.setAmount(updatePaymentRequestDto.getAmount());
        paymentEntity.setDescription(updatePaymentRequestDto.getDescription());
    }
}

