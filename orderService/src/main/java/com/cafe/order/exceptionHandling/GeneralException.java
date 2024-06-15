package com.cafe.order.exceptionHandling;

import org.springframework.stereotype.Component;

@Component
public class GeneralException extends RuntimeException{
    public GeneralException() {
        super();
    }

    public GeneralException(String message) {
        super(message);
    }
}
