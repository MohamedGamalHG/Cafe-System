package com.cafe.product.repositories;

import com.cafe.product.domain.entities.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductJpa,Long> {
}
