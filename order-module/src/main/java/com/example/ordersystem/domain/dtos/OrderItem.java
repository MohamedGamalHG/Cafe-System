package com.example.ordersystem.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderItem {
    private Long Id;
    private long     orderId;
    private long     productId;
    private int     quantity;
    private String  notes;
}
