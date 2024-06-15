package com.cafe.order.services;

import com.cafe.order.domain.dtos.Order;
import com.cafe.order.domain.dtos.OrderItem;
import com.cafe.order.domain.dtos.ProductResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceServiceImp implements PriceService{
    private final ProductProvider provider;

    public PriceServiceImp(ProductProvider productProvider){
        this.provider = productProvider;
    }
    @Override
    public double getTotalPrice(Order Order)
    {
        return Order.getOrderItemList().stream().mapToDouble(orderitem -> orderitem.getQuantity() * getProductPriceById(orderitem.getProductId(),Order)).sum();
    }
    @Override
    public double getProductPriceById(long id,Order Order)
    {
        Map<Long,Double> priceMap = mapIdToPrice(Order);
        return priceMap.get(id);
    }
    private Map<Long,Double> mapIdToPrice(Order Order)
    {
        List<Long> ids = getProductIds(Order);

        List<ProductResponse> productResponseList = provider.fetchProductDataByIds(ids);

        Map<Long,Double> priceMap = new HashMap<>();
        for (ProductResponse productResponse:productResponseList)
        {
            if(!priceMap.containsKey(productResponse.getId()))
                priceMap.put(productResponse.getId(),productResponse.getPrice());
        }

        return priceMap;
    }
    private List<Long> getProductIds(Order Order)
    {
        List<Long> ids = new ArrayList<>();

        for (OrderItem orderItem:Order.getOrderItemList()) {
            ids.add(orderItem.getProductId());
        }
        return ids;
    }
}
