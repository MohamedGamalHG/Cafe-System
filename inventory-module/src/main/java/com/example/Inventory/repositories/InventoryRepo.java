package com.example.Inventory.repositories;

import com.example.Inventory.domain.entities.InventoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryJpa,Long> {
}
