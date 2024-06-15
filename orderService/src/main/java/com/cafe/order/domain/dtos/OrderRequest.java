package com.cafe.order.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private long     productId;
    private int     quantity;
    private String  notes;
}
