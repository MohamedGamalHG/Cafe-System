package com.example.ordersystem.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class JpaOrder extends BaseEntity{
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "order_status")
    private int orderStatus;
    @Column(name = "total_amount")
    private double totalAmount;
}

