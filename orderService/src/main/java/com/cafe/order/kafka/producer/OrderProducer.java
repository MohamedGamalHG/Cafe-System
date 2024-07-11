package com.cafe.order.kafka.producer;

import com.cafe.kafka.KafkaResponse;

import java.util.List;

public interface OrderProducer {

//    void sendOrderMessage(String message);

//    void sendOrderMessage(KafkaResponse kafkaResponse);

    void sendOrderMessage(List<KafkaResponse> kafkaResponse);

}
