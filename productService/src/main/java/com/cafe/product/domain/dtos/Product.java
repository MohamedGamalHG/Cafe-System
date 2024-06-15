package com.cafe.product.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Long Id;
    private String Name;
    private double Price;
    private String ImagePath;
    private String Description;
    private Long CategoryId;
}
