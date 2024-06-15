package com.cafe.order.domainMap;

import com.cafe.order.domain.dtos.OrderRequestList;
import com.cafe.order.domain.entities.JpaOrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    private final ModelMapper modelMapper;

    public OrderItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public OrderRequestList convert(JpaOrderItem jpaOrderItem)
    {
        return modelMapper.map(jpaOrderItem, OrderRequestList.class);
    }
    public JpaOrderItem convert(OrderRequestList orderRequestList)
    {
        return modelMapper.map(orderRequestList, JpaOrderItem.class);
    }
}
