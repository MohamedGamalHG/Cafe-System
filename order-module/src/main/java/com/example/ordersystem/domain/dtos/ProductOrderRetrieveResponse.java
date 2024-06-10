package com.example.ordersystem.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductOrderRetrieveResponse {
    private Long Id;
    private String Name;
    private double Price;
}
