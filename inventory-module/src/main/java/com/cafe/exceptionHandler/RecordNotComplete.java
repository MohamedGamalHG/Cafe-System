package com.cafe.exceptionHandler;

public class RecordNotComplete extends RuntimeException{
    public RecordNotComplete() {
    }

    public RecordNotComplete(String message) {
        super(message);
    }

    public RecordNotComplete(Throwable cause) {
        super(cause);
    }
}
