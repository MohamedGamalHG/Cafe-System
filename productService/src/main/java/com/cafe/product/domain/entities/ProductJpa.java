package com.cafe.product.domain.entities;

import jakarta.persistence.*;
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
    @Column(name = "image_path")
    private String imagePath;
    private String Description;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryJpa CategoryJpa;

}
