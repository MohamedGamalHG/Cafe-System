package com.cafe.order.services;

import com.cafe.order.domain.dtos.OrderRequest;
import com.cafe.order.domain.dtos.OrderRequestList;
import com.cafe.order.domainMap.OrderItemMapper;
import com.cafe.order.domainMap.OrderMapper;
import com.cafe.order.exceptionHandling.GeneralException;
import com.cafe.order.exceptionHandling.RecordNotFoundException;
import com.cafe.order.repositories.OrderRepository;
import com.cafe.order.domain.dtos.Order;
import com.cafe.order.domain.dtos.OrderItem;
import com.cafe.order.domain.entities.JpaOrder;
import com.cafe.order.domain.entities.JpaOrderItem;
import com.cafe.order.repositories.OrderItemRepository;
import com.cafe.order.enums.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository repository;
    private OrderItemRepository orderItemRepository;
    private final com.cafe.order.domainMap.OrderMapper OrderMapper;
    private final OrderItemMapper orderItemMapper;
    private final PriceService priceService;

    public List<Order> findAll()
    {
        // without use stream
        /*
            List<Order> Orders = new ArrayList<>();
            for (JpaOrder Order:repository.findAll()) {
                Orders.add(OrderMapper.convert(Order));
            }
        */

        List<Order> Orders = repository.findAll().stream().map(e -> OrderMapper.convert(e)).toList();
        return Orders;
    }
    public Order findById(long id)
    {
        Optional<JpaOrder> jpaOrder = repository.findById(id);
        if (jpaOrder.isPresent())
        {
            return OrderMapper.convert(jpaOrder.get());
        }
        throw new RecordNotFoundException("This Record Is Not Found Of Id = "+ id);
    }

    @Transactional
    public OrderRequest create(OrderRequest orderRequest)
    {

        JpaOrder order = createOrder(orderRequest);
        repository.save(order);

        List<JpaOrderItem> jpaOrderItems = createOrderItemList(orderRequest,order);
        orderItemRepository.saveAll(jpaOrderItems);

        return orderRequest;
    }

    @Transactional
    public void delete(Long id)
    {
        try{
            boolean isDeleted = deleteOrderAndOrderItems(id);
            if(!isDeleted)
                throw new GeneralException("Something went wrong when delete order = " + id);
        }
        catch (RecordNotFoundException ex) {
            throw new RecordNotFoundException("This Record Is Not Found Of Id = " + id);
        }

    }
    private boolean deleteOrderAndOrderItems(Long orderId) {

        Optional<JpaOrder> jpaOrder = repository.findById(orderId);
        if (jpaOrder.isPresent()) {

            Optional<List<JpaOrderItem>> orderItemList = orderItemRepository.findByOrderId(jpaOrder.get());
            if (orderItemList.isPresent()) {
                List<Long> ids = new ArrayList<>();
                for (JpaOrderItem jpaOrderItem : orderItemList.get())
                    ids.add(jpaOrderItem.getId());
                orderItemRepository.deleteAllByIdInBatch(ids);
            }
            repository.deleteById(orderId);
            return true;
        }
        return false;
    }

    private JpaOrder createOrder(OrderRequest orderRequest)
    {
        Order Order = new Order();
        Order.setOrderDate(LocalDateTime.now());
        Order.setOrderStatus(Integer.parseInt(OrderStatus.Placed.toString()));
        Order.setTotalAmount(priceService.getTotalPrice(orderRequest));

        return OrderMapper.convert(Order);
    }


    private List<JpaOrderItem> createOrderItemList(OrderRequest orderRequest,JpaOrder order)
    {
        List<JpaOrderItem> jpaOrderItems = new ArrayList<>();

        for (OrderRequestList orderItem:orderRequest.getOrderRequestLists()) {
            orderItem.setOrderId(order);
            orderItem.setProductPrice(priceService.getProductPriceById(orderItem.getProductId()));
            JpaOrderItem jpaOrderItem = orderItemMapper.convert(orderItem);
            jpaOrderItems.add(jpaOrderItem);
        }
        return jpaOrderItems;
    }

}
