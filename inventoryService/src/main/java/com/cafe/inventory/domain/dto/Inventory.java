package com.cafe.inventory.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {
    private Long Id;
    private String productName;
    private Long quantity;
}
