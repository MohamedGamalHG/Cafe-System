package com.cafe.order.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Value("${spring.kafka.topic-name-1}")
    private String TopicName;
    @Bean
    public NewTopic firstOrderTopic()
    {
        return TopicBuilder.name(TopicName).build();
    }

    @Bean
    public NewTopic secondOrderTopic()
    {
        return TopicBuilder.name("order-topic-2").partitions(3).build();
    }
}
