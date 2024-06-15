package com.cafe.order.repositories;

import com.cafe.order.domain.entities.JpaOrder;
import com.cafe.order.domain.entities.JpaOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
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
