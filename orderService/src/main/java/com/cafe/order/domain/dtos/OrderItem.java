package com.cafe.order.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long Id;
    private long     orderId;
    private long     productId;
    private int     quantity;
    private String  notes;
}
