package com.cafe.services;

import com.cafe.domain.dtos.Order;

public interface PriceService {
    double getTotalPrice(Order order);
}
