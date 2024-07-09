package com.cafe.order.kafka.producer;

import com.cafe.kafka.KafkaResponse;

public interface OrderProducer {
//    void sendOrderMessage(String message);
    void sendOrderMessage(KafkaResponse kafkaResponse);

}
