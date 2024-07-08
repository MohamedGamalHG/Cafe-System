package com.cafe.order.kafka.producer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
//@AllArgsConstructor
public class OrderProducerImp implements OrderProducer{
    @Value("${spring.kafka.topic-name-1}")
    private String topicName;
    private KafkaTemplate<String, ProductProducerResponse> kafkaTemplate;
    public OrderProducerImp(KafkaTemplate<String, ProductProducerResponse> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

//    public void sendOrderMessage(String message) {
//        kafkaTemplate.send(this.topicName,message);
//    }

    public void sendOrderMessage(ProductProducerResponse productProducerResponse) {

        Message<ProductProducerResponse> productProducerResponseMessage =
                MessageBuilder
                .withPayload(productProducerResponse)
                .setHeader(KafkaHeaders.TOPIC,topicName)
                .build();
        kafkaTemplate.send(productProducerResponseMessage);
//        kafkaTemplate.send(this.topicName,productProducerResponse);
    }
}
