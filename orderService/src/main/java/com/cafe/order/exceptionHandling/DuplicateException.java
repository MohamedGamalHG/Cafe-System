package com.cafe.order.exceptionHandling;

import org.springframework.stereotype.Component;

@Component
public class DuplicateException extends RuntimeException{
    public DuplicateException() {
        super();
    }

    public DuplicateException(String message) {
        super(message);
    }
}
