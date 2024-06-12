package com.cafe.services;

import com.cafe.domain.dtos.Order;
import com.cafe.domain.dtos.OrderItem;
import com.cafe.domain.dtos.ProductResponse;
import com.cafe.domain.entities.JpaOrder;
import com.cafe.domain.entities.JpaOrderItem;
import com.cafe.domainMap.OrderMapper;
import com.cafe.exceptionHandling.GeneralException;
import com.cafe.exceptionHandling.RecordNotFoundException;
import com.cafe.repositories.OrderItemRepository;
import com.cafe.repositories.OrderRepository;
import com.cafe.enums.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    private OrderRepository repository;
    private OrderItemRepository orderItemRepository;
    private final com.cafe.domainMap.OrderMapper OrderMapper;
    private final ProductProviderImp productService;
    private final PriceService priceService;

    public OrderService(OrderRepository orderRepository ,
                        OrderMapper OrderMapper,
                        OrderItemRepository orderItemRepository,
                        ProductProviderImp productService,
                        PriceService priceService)
    {
        this.repository = orderRepository;
        this.OrderMapper = OrderMapper;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.priceService = priceService;
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

    @Transactional
    public Order create(Order Order)
    {
        /*
        int i=0;
        for(ProductResponse p : productResponseList)
            totalPrice = totalPrice + (p.getPrice() *Order.getOrderItemList().get(i++).getQuantity()) ;
*/

        JpaOrder order = createOrder(Order);
        repository.save(order);

        List<JpaOrderItem> jpaOrderItems = createOrderItemList(Order,order);
        orderItemRepository.saveAll(jpaOrderItems);

        return Order;
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

    private JpaOrder createOrder(Order Order)
    {
        Order.setOrderDate(LocalDateTime.now());
        Order.setOrderStatus(Integer.parseInt(OrderStatus.Placed.toString()));
        Order.setTotalAmount(priceService.getTotalPrice(Order));

        return OrderMapper.convert(Order);
    }


    private List<JpaOrderItem> createOrderItemList(Order Order,JpaOrder order)
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

}
