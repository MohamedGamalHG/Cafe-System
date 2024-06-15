package com.cafe.order.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse {
    private Long Id;
    private String Name;
    private double Price;
}
