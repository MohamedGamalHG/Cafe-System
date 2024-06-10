package com.example.ordersystem
.services;

import com.example.ordersystem.domain.dtos.Order;
import com.example.ordersystem.domain.dtos.OrderItem;
import com.example.ordersystem.domain.dtos.ProductOrderRetrieveResponse;
import com.example.ordersystem.domain.entities.JpaOrder;
import com.example.ordersystem.domain.entities.JpaOrderItem;
import com.example.ordersystem.domainMap.OrderMapper;
import com.example.ordersystem.enums.OrderStatus;
import com.example.ordersystem.exceptionHandling.GeneralException;
import com.example.ordersystem.exceptionHandling.RecordNotFoundException;
import com.example.ordersystem.repositories.OrderItemRepository;
import com.example.ordersystem.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository repository;
    private OrderItemRepository orderItemRepository;
    private final OrderMapper OrderMapper;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository ,
                        OrderMapper OrderMapper,
                        OrderItemRepository orderItemRepository,
                        ProductService productService)
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

    public Order create(Order OrderData)
    {
        double totalPrice = 0.0;
        List<Long>ids= new ArrayList<>();
        for (OrderItem order1:OrderData.getOrderItemList()) {
            ids.add(order1.getProductId());
        }
        List<ProductOrderRetrieveResponse> productRetrieveData = productService.FetchDataFromProduct("/product/retrieveByIds",ids);

        OrderData.setOrderDate(LocalDateTime.now());
        OrderData.setOrderStatus(Integer.parseInt(OrderStatus.Placed.toString()));
        int i=0;
        for(ProductOrderRetrieveResponse p : productRetrieveData)
            totalPrice = totalPrice + (p.getPrice() *OrderData.getOrderItemList().get(i++).getQuantity()) ;

        OrderData.setTotalAmount(totalPrice);

        JpaOrder order = OrderMapper.convert(OrderData);
        repository.save(order);
        List<JpaOrderItem> jpaOrderItems = new ArrayList<>();

        for (OrderItem order1:OrderData.getOrderItemList()) {
            JpaOrderItem jpaOrderItem =  new JpaOrderItem();

            jpaOrderItem.setOrderId(order);
            jpaOrderItem.setQuantity(order1.getQuantity());
            jpaOrderItem.setNotes(order1.getNotes());
            jpaOrderItem.setProductId(order1.getProductId());

            jpaOrderItems.add(jpaOrderItem);
        }
        orderItemRepository.saveAll(jpaOrderItems);

        return OrderData;
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
