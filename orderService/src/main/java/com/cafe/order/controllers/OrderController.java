package com.cafe.order.controllers;

import com.cafe.order.domain.dtos.OrderRequest;
import com.cafe.order.services.OrderService;
import com.cafe.order.domain.dtos.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(orderService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(orderService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest)
    {
        return ResponseEntity.ok(orderService.create(orderRequest));
    }
    @DeleteMapping("/{id}")
    public  boolean delete(@PathVariable Long id)
    {
        orderService.delete(id);
        return true;
    }

}
