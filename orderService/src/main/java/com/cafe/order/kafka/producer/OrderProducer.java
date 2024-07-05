package com.cafe.order.kafka.producer;

public interface OrderProducer {
    void sendOrderMessage(String message);

}
