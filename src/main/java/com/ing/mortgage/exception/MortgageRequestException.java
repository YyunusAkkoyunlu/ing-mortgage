package com.ing.mortgage.exception;

public class MortgageRequestException extends RuntimeException{

    public MortgageRequestException(String message) {
        super(message);
    }

    public MortgageRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
