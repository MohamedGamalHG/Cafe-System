package com.cafe.order.services;

import com.cafe.order.domain.dtos.Order;

public interface PriceService {
    double getTotalPrice(Order order);
    double getProductPriceById(long id,Order order);
}
