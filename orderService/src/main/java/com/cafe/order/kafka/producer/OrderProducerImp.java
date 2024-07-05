package com.cafe.order.kafka.producer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
//@AllArgsConstructor
public class OrderProducerImp implements OrderProducer{
    @Value("${spring.kafka.topic-name-1}")
    private String topicName;
    private KafkaTemplate<String, String> kafkaTemplate;
    public OrderProducerImp(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderMessage(String message) {
        kafkaTemplate.send(this.topicName,message);
    }
}
