package com.cafe.repositories;

import com.cafe.domain.entities.CategoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo  extends JpaRepository<CategoryJpa,Long> {
}
