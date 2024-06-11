package com.cafe.exceptionHandler;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException() {
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }
}
