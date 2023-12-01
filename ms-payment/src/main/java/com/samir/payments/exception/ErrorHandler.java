package com.samir.payments.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.samir.payments.model.constant.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static com.samir.payments.model.constant.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    // Error-ları tutmaq istədiyimiz method-a throw new RuntimeException() yazırıq.
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex) {
        log.error("Exception: ", ex);
        return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(NotFoundException ex) {
        log.error("NotFoundException: ", ex);
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }
}
