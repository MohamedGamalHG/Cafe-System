package com.cafe.order.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class JpaOrderItem extends BaseEntity{
    private int quantity;
    private String notes;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private JpaOrder orderId;
    @Column(name = "product_id")
    private long productId;
    @Column(name = "product_price")
    private double productPrice;
}
