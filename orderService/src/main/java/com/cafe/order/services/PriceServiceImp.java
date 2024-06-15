package com.cafe.order.services;

import com.cafe.order.domain.dtos.*;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class PriceServiceImp implements PriceService{
    private final ProductProvider productProvider;
    private Map<Long,Double> priceMap;
    @Override
    public double getTotalPrice(OrderRequest orderRequest)
    {
         priceMap = mapIdToPrice(orderRequest);

        return orderRequest
                .getOrderRequestLists()
                .stream()
                .mapToDouble(orderItem -> orderItem.getQuantity() * priceMap.get(orderItem.getProductId())).sum();
//                .mapToDouble(orderItem -> orderItem.getQuantity() * getProductPriceById(orderItem.getProductId())).sum();
    }
    @Override
    public double getProductPriceById(long id)
    {
        return priceMap.get(id);
    }
    private Map<Long,Double> mapIdToPrice(OrderRequest orderRequest)
    {
        List<Long> ids = getProductIds(orderRequest);

        List<Product> productResponseList = productProvider.fetchProductDataByIds(ids);

        Map<Long,Double> priceMap = new HashMap<>();
        for (Product productResponse:productResponseList)
        {
            if(!priceMap.containsKey(productResponse.getId()))
                priceMap.put(productResponse.getId(),productResponse.getPrice());
        }

        return priceMap;
    }
    private List<Long> getProductIds(OrderRequest orderRequest)
    {
        List<Long> ids = new ArrayList<>();

        for (OrderRequestList orderItem:orderRequest.getOrderRequestLists()) {
            ids.add(orderItem.getProductId());
        }
        return ids;
    }
}
