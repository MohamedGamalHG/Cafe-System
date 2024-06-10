package com.example.ordersystem
.repositories;

import com.example.ordersystem.domain.entities.JpaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<JpaOrder,Long> {
}
