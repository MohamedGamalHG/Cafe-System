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

//    @PostMapping(value = "/",produces = {"text/event-stream"})
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest)
    {
        OrderRequest book = orderService.create(orderRequest);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public  boolean delete(@PathVariable Long id)
    {
        orderService.delete(id);
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
