package com.cafe.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderSendResponse {
    private Long Id;
    private String Name;
    private double Price;
}
