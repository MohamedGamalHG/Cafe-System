package com.cafe.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {
    private Long Id;
    private String ProductName;
    private Long Quantity;
}
