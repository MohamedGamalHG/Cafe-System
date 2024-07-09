package com.cafe.inventory.kafka.consumer;

import com.cafe.kafka.KafkaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryConsumerImp {

    Logger log = LoggerFactory.getLogger(InventoryConsumerImp.class);
//    @KafkaListener(topics = "order-topic-1", groupId = "inventory-group1")
////    @KafkaListener(topics = "topic-1,topic-2", groupId = "group1") // more than one topic to listen
//
//    void listener(String data) {
//        log.info("Received message [{}] in group1", data);
//    }

    @KafkaListener(
            topics = "order-topic-7",
            groupId = "inventory-group1",
            containerFactory = "consumerKafkaListenerContainerFactory")
//    @KafkaListener(topics = "topic-1,topic-2", groupId = "group1") // more than one topic to listen

    void listener(KafkaResponse kafkaResponse) {
        log.info("Received message [{}] in group1 = product Name is => ", kafkaResponse.getProductName() + " and quantity is => " + kafkaResponse.getQuantity());


    }
}
