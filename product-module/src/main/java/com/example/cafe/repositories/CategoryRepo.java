package com.example.cafe.repositories;

import com.example.cafe.domain.entities.CategoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo  extends JpaRepository<CategoryJpa,Long> {
}
