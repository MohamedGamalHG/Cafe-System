package com.cafe.inventory.repositories;

import com.cafe.inventory.domain.entities.InventoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryJpa,Long> {

    InventoryJpa findByProductName(String Name);
}
