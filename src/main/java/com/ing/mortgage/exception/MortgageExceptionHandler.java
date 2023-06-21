package com.ing.mortgage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class MortgageExceptionHandler {

    @ExceptionHandler(value = {MortgageRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(MortgageRequestException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        MortgageException apiException = new MortgageException(exception.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }

}
