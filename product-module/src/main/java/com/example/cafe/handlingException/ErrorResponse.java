package com.example.cafe.handlingException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private boolean status;
    private String message;
    private LocalDateTime time;
    private List<String> details;

    public ErrorResponse(String message,List<String> details)
    {
        status = Boolean.FALSE;
        time = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
