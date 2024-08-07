package com.cafe.inventory.kafka.consumer;

import com.cafe.kafka.KafkaResponse;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public ConsumerFactory<String, KafkaResponse> consumerFactory()
    {
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put("spring.json.trusted-packages", "com.cafe.kafka");  // Trust the package
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.cafe.kafka");  // Trust the package

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer(KafkaResponse.class));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaResponse> consumerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
