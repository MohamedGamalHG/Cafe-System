package com.cafe.order.repositories;

import com.cafe.order.domain.entities.JpaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<JpaOrder,Long> {
}
