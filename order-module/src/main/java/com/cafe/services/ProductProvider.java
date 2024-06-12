package com.cafe.services;

import com.cafe.domain.dtos.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductProvider {
    List<ProductResponse> fetchProductDataByIds(List<Long> ids);
}
