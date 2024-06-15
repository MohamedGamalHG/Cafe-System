package com.cafe.order.domain.dtos;

import com.cafe.order.domain.entities.JpaOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestList {
    private Long Id;
    private JpaOrder orderId;
    private long     productId;
    private int     quantity;
    private String  notes;
    @JsonIgnore
    private double productPrice;
}
