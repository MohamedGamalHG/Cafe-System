package com.cafe.services;

import com.cafe.domain.dtos.ProductOrderRetrieveResponse;

import java.util.List;
import java.util.Optional;

public interface ProductProvider {
    List<ProductOrderRetrieveResponse> fetchProductDataByIds(List<Long> ids);
}
