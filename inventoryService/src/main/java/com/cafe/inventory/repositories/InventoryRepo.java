package com.cafe.inventory.repositories;

import com.cafe.inventory.domain.entities.InventoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryJpa,Long> {
}
