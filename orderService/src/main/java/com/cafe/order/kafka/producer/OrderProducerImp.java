package com.cafe.order.kafka.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderProducerImp implements OrderProducer{
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }
}
