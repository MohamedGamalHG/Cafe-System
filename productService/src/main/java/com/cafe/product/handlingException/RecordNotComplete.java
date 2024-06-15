package com.cafe.product.handlingException;

public class RecordNotComplete extends RuntimeException{
    public RecordNotComplete() {
    }

    public RecordNotComplete(String message) {
        super(message);
    }

    public RecordNotComplete(String message, Throwable cause) {
        super(message, cause);
    }
}
