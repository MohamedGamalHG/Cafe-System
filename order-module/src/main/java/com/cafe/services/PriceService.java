package com.cafe.services;

import com.cafe.domain.dtos.Order;

public interface PriceService {
    double getTotalPrice(Order order);
    double getProductPriceById(long id,Order order);
}
