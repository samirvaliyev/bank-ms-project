package com.samir.payments.service;

import com.samir.payments.client.CountryClient;
import com.samir.payments.dao.entity.PaymentEntity;
import com.samir.payments.dao.repository.PaymentRepository;
import com.samir.payments.exception.NotFoundException;
import com.samir.payments.model.request.PaymentCriteria;
import com.samir.payments.model.request.PaymentRequestDto;
import com.samir.payments.model.response.PageablePaymentResponse;
import com.samir.payments.model.response.PaymentResponseDto;
import com.samir.payments.model.request.UpdatePaymentRequestDto;
import com.samir.payments.mapper.PaymentMapper;
import com.samir.payments.service.specification.PaymentSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.samir.payments.model.constant.ExceptionConstants.COUNTRY_NOT_FOUND_CODE;
import static com.samir.payments.model.constant.ExceptionConstants.COUNTRY_NOT_FOUND_MESSAGE;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CountryClient countryClient;

    // Create payment
    public void savePayment(PaymentRequestDto paymentRequestDto) {

        log.info("ActionLog.savePayment.start");

        countryClient.getAvailableCountries(paymentRequestDto.getCurrency())
                .stream()
                .filter(country -> country.getRemainingLimit().compareTo(paymentRequestDto.getAmount()) > 0)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format(
                        COUNTRY_NOT_FOUND_MESSAGE,
                        paymentRequestDto.getAmount(),
                        paymentRequestDto.getCurrency()),
                        COUNTRY_NOT_FOUND_CODE));

        paymentRepository.save(PaymentMapper.mapRequestToEntity(paymentRequestDto));
        log.info("ActionLog.savePayment.success");
    }

    // Get payment by id
    public PaymentResponseDto getPaymentById(Long id) {

        log.info("ActionLog.getPaymentById.start id:{}", id);

        PaymentEntity paymentEntity = fetchPaymentIfExists(id);
        log.info("ActionLog.getPaymentById.success id:{}", id);

        return PaymentMapper.mapEntityToResponse(paymentEntity);
    }

    // Get payments
    public PageablePaymentResponse getPayments(int page,
                                               int count,
                                               PaymentCriteria paymentCriteria) {

        log.info("ActionLog.getPayments.start");

        PageRequest pageable = PageRequest.of(page, count, Sort.by(DESC, "id"));

        var pageablePayments =
                paymentRepository.findAll(
                        new PaymentSpecification(paymentCriteria), pageable);
//                .stream()
//                .map(PaymentMapper::mapEntityToResponse)
//                .toList();

        var payments = pageablePayments.getContent()
                .stream()
                .map(PaymentMapper::mapEntityToResponse)
                .toList();

        log.info("ActionLog.getPayments.success");

        PageablePaymentResponse pageablePaymentResponse = new PageablePaymentResponse();

        pageablePaymentResponse.setPaymentResponseDto(payments);
        pageablePaymentResponse.setTotalPages(pageablePayments.getTotalPages());
        pageablePaymentResponse.setTotalElements(pageablePayments.getTotalElements());
        pageablePaymentResponse.setHasNextPage(pageablePayments.hasNext());

        return pageablePaymentResponse;
    }

    // Update payment by id
    public void updatePaymentById(Long id, UpdatePaymentRequestDto updatePaymentRequestDto) {

        log.info("ActionLog.updatePaymentById.start id:{}", id);

        PaymentEntity paymentEntity = fetchPaymentIfExists(id);
        PaymentMapper.updatePaymentById(updatePaymentRequestDto, paymentEntity);
        paymentRepository.save(paymentEntity);

        log.info("ActionLog.updatePaymentById.success id:{}", id);
    }

    // Delete payment by id
    public void deletePaymentById(Long id) {

        log.info("ActionLog.deletePaymentById.start id: {}", id);

        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            log.info("ActionLog.deletePaymentById.success id: {}", id);
        } else {
            log.warn("ActionLog.deletePaymentById.notFound id: {}", id);
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

    // Fetch payment if exists
    private PaymentEntity fetchPaymentIfExists(Long id) {

        log.info("ActionLog.fetchPaymentIfExists.start id: {}", id);

        return paymentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.fetchPaymentIfExists.notFound id: {}", id);
                    return new RuntimeException("Payment not found with id: " + id);
                });
    }
}

