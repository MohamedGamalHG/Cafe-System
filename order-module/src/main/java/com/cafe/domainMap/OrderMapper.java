package com.cafe.domainMap;

import com.cafe.domain.dtos.Order;
import com.cafe.domain.entities.JpaOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        TypeMap<Order, JpaOrder> type = this.modelMapper.createTypeMap(Order.class, JpaOrder.class);
//        modelMapper.addMappings(new PropertyMap<Order, JpaOrder>() {
//            @Override
//            protected void configure() {
//                map(source.setOrder_date();, (destination, value) -> value != null ? LocalDateTime.of(value) : null).setNullRepresentation(null); // Handle null values
//            }
//        });
//        type.addMappings(mapper -> {
//            mapper.map(src -> src.getOrder_status(), (jpaOrder, o) -> jpaOrder.setOrder_status(Or));
//            //mapper.map(src -> src.getId(), CategoryJpa::setId);
//        });
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
