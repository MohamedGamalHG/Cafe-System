package com.cafe.services;

import com.cafe.domain.dtos.Order;
import com.cafe.domain.dtos.OrderItem;
import com.cafe.domain.dtos.ProductOrderRetrieveResponse;
import com.cafe.domain.entities.JpaOrder;
import com.cafe.domain.entities.JpaOrderItem;
import com.cafe.domainMap.OrderMapper;
import com.cafe.exceptionHandling.GeneralException;
import com.cafe.exceptionHandling.RecordNotFoundException;
import com.cafe.repositories.OrderItemRepository;
import com.cafe.repositories.OrderRepository;
import com.cafe.enums.OrderStatus;
//import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository repository;
    private OrderItemRepository orderItemRepository;
    private final com.cafe.domainMap.OrderMapper OrderMapper;
    private final ProductProviderImp productService;

    public OrderService(OrderRepository orderRepository ,
                        OrderMapper OrderMapper,
                        OrderItemRepository orderItemRepository,
                        ProductProviderImp productService)
    {
        this.repository = orderRepository;
        this.OrderMapper = OrderMapper;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }
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

    public Order create(Order Order)
    {
        double totalPrice = 0.0;

        List<ProductOrderRetrieveResponse> productRetrieveData = productService.fetchProductDataByIds(ids);

        Order.setOrderDate(LocalDateTime.now());
        Order.setOrderStatus(Integer.parseInt(OrderStatus.Placed.toString()));
        int i=0;
        for(ProductOrderRetrieveResponse p : productRetrieveData)
            totalPrice = totalPrice + (p.getPrice() *Order.getOrderItemList().get(i++).getQuantity()) ;

        Order.setTotalAmount(totalPrice);

        JpaOrder order = OrderMapper.convert(Order);
        repository.save(order);

        List<JpaOrderItem> jpaOrderItems = createOrderList(Order,order);
        orderItemRepository.saveAll(jpaOrderItems);


        return Order;
    }
    private List<Long> getProductIds(Order Order)
    {
        List<Long> ids = new ArrayList<>();

        for (OrderItem orderItem:Order.getOrderItemList()) {
            ids.add(orderItem.getProductId());
        }
        return ids;
    }
    private List<JpaOrderItem> createOrderList(Order Order,JpaOrder order)
    {
        List<JpaOrderItem> jpaOrderItems = new ArrayList<>();

        for (OrderItem orderItem:Order.getOrderItemList()) {
            JpaOrderItem jpaOrderItem =  new JpaOrderItem();

            jpaOrderItem.setOrderId(order);
            jpaOrderItem.setQuantity(orderItem.getQuantity());
            jpaOrderItem.setNotes(orderItem.getNotes());
            jpaOrderItem.setProductId(orderItem.getProductId());

            jpaOrderItems.add(jpaOrderItem);
        }
        return jpaOrderItems;
    }
    public Order update(Order Order)
    {
        try {
            Optional<JpaOrder> jpaOrder = repository.findById(Order.getId());

            if (jpaOrder.isPresent()) {
                JpaOrder jpaOrder1 = OrderMapper.convert(Order);
                repository.save(jpaOrder1);
                return Order;
            }
            else
                throw new RecordNotFoundException("This Record Is Not Found Of Id = "+Order.getId());
        }catch (Exception ex)
        {
            //logger.error(ex.getMessage());
            throw new GeneralException(ex.getMessage());
        }
    }
    @Transactional
    public void delete(Long id)
    {
        Optional<JpaOrder> jpaOrder = repository.findById(id);
        if(jpaOrder.isPresent()) {

            Optional<List<JpaOrderItem>> orderItemList = orderItemRepository.findByOrderId(jpaOrder.get());
            if(orderItemList.isPresent()) {
                List<Long>ids = new ArrayList<>();
                for (JpaOrderItem jpaOrderItem : orderItemList.get())
                    ids.add(jpaOrderItem.getId());
                orderItemRepository.deleteAllByIdInBatch(ids);
            }
            repository.deleteById(id);

        }
        else
            throw new RecordNotFoundException("This Record Is Not Found Of Id = "+id);

    }

}
