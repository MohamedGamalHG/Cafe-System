package com.example.cafe.repositories;

import com.example.cafe.domain.entities.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductJpa,Long> {
}
