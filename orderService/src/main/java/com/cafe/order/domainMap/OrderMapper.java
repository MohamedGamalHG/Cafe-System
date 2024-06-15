package com.cafe.order.domainMap;

import com.cafe.order.domain.dtos.Order;
import com.cafe.order.domain.entities.JpaOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Order convert(JpaOrder jpaOrder)
    {
        return modelMapper.map(jpaOrder, Order.class);
    }
    public JpaOrder convert(Order Order)
    {
        return modelMapper.map(Order, JpaOrder.class);
    }
}
