package com.cafe.order.services;

import com.cafe.order.domain.dtos.ProductResponse;

import java.util.List;

public interface ProductProvider {
    List<ProductResponse> fetchProductDataByIds(List<Long> ids);
}
