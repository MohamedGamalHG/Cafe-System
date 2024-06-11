package com.cafe.domain.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
