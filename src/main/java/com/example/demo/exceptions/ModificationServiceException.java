package com.example.demo.exceptions;

public class ModificationServiceException extends Exception {
    public ModificationServiceException() {
    }

    public ModificationServiceException(String message) {
        super(message);
    }

    public ModificationServiceException(Throwable cause) {
        super(cause);
    }
}
