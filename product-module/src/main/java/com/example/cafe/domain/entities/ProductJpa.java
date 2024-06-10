package com.example.cafe.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductJpa extends BaseEntity {
    private double Price;
    private String ImagePath;
    private String Description;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryJpa CategoryJpa;

}
