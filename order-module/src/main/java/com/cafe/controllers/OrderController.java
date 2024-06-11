package com.cafe.controllers;

import com.cafe.domain.dtos.Order;
import com.cafe.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService orderService){
        this.service = orderService;
    }

    @GetMapping
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.findById(id));
    }

//    @PostMapping(value = "/",produces = {"text/event-stream"})
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Order orderinsert)
    {
        Order book = service.create(orderinsert);
        return ResponseEntity.ok(book);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Order orderupdate)
    {
        Order book = service.update(orderupdate);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public  boolean delete(@PathVariable Long id)
    {
        service.delete(id);
        return true;
    }

    @GetMapping(value = "/test",produces = {"text/event-stream"})
    public Mono<ResponseEntity<Integer>> test()
    {
        return Mono.just(ResponseEntity.ok(20));
    }

    @GetMapping(value = "/test2",produces = {"text/event-stream"})
    public Flux<ResponseEntity<Integer>> test2() throws InterruptedException
    {
        return Flux.create( fluxList-> {
            for (int i = 0; i < 20; i++) {
                fluxList.next(ResponseEntity.ok(i));
                try{
                    Thread.sleep(500);
                }catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }

            }
            fluxList.complete();
        });
    }
}
