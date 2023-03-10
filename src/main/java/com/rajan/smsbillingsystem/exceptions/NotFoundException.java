package com.rajan.smsbillingsystem.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
