package com.cafe.order.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    private Long Id;
    private LocalDateTime   orderDate;
    private int             orderStatus;
    private double          totalAmount;
    private List<OrderItem> orderItemList = new ArrayList<>();
}
