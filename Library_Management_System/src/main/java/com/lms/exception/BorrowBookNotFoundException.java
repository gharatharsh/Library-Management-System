package com.lms.exception;

public class BorrowBookNotFoundException extends RuntimeException {

    public BorrowBookNotFoundException() {
        super();
    }

    public BorrowBookNotFoundException(String message) {
        super(message);
    }
}