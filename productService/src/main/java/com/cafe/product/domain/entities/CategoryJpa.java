package com.cafe.product.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryJpa extends BaseEntity {

    @OneToMany(mappedBy = "CategoryJpa",fetch = FetchType.LAZY)
    private Set<ProductJpa> productJpas = new HashSet<>();
}
