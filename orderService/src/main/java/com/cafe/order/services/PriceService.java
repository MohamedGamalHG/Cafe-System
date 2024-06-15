package com.cafe.order.services;

import com.cafe.order.domain.dtos.Order;
import com.cafe.order.domain.dtos.OrderRequest;

public interface PriceService {
    double getTotalPrice(OrderRequest orderRequest);
    double getProductPriceById(long id);
}
