package com.cafe.order.services;

import com.cafe.order.domain.dtos.Product;

import java.util.List;

public interface ProductProvider {
    List<Product> fetchProductDataByIds(List<Long> ids);
}
