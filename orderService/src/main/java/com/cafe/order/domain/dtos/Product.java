package com.cafe.order.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Long Id;
    private String Name;
    private double Price;
}
