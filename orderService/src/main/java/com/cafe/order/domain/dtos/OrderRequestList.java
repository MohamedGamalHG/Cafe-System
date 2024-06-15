package com.cafe.order.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestList {
    private long     productId;
    private int     quantity;
    private String  notes;
}
