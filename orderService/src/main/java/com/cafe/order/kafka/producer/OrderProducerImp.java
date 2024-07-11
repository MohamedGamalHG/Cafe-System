package com.cafe.order.kafka.producer;

import com.cafe.kafka.KafkaResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
//@AllArgsConstructor
public class OrderProducerImp implements OrderProducer{
    @Value("${spring.kafka.topic-name-1}")
    private String topicName;
    private KafkaTemplate<String, KafkaResponse> kafkaTemplate;
    public OrderProducerImp(KafkaTemplate<String, KafkaResponse> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }


//    public void sendOrderMessage(String message) {
//        kafkaTemplate.send(this.topicName,message);
//    }


//    public void sendOrderMessage(KafkaResponse productProducerResponse) {
//
//        Message<KafkaResponse> productProducerResponseMessage =
//                MessageBuilder
//                .withPayload(productProducerResponse)
//                .setHeader(KafkaHeaders.TOPIC,topicName)
//                .build();
//        kafkaTemplate.send(productProducerResponseMessage);
////        kafkaTemplate.send(this.topicName,productProducerResponse);
//    }

    public void sendOrderMessage(List<KafkaResponse> productProducerResponse) {

        for (KafkaResponse ka:productProducerResponse) {
            Message<KafkaResponse> productProducerResponseMessage =
                    MessageBuilder
                    .withPayload(ka)
                    .setHeader(KafkaHeaders.TOPIC,topicName)
                    .build();
            kafkaTemplate.send(productProducerResponseMessage);
        }

//        kafkaTemplate.send(this.topicName,productProducerResponse);
    }
}
