package com.example.ordersystem
.repositories;

import com.example.ordersystem.domain.entities.JpaOrder;
import com.example.ordersystem.domain.entities.JpaOrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<JpaOrderItem,Long> {
    Optional<List<JpaOrderItem>> findByOrderId(JpaOrder id);

//    @Modifying
//    @Transactional
//    @Query(value = "delete from JpaOrderItem where order_id in (:ids)")
//    void deleteAllByOrderId(@Param("ids") List<Long> ids);
}
