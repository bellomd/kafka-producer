package com.bellomhd.kafka.config;

import com.bellomhd.kafka.MessageVo;
import com.bellomhd.kafka.producer.MessageProducerListener;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private final MessageProducerListener messageProducerListener;

    public ProducerConfig(MessageProducerListener messageProducerListener) {
        this.messageProducerListener = messageProducerListener;
    }

    @Bean
    public KafkaTemplate<String, MessageVo> kafkaTemplate() {
        final KafkaTemplate<String, MessageVo> kafkaTemplate = new KafkaTemplate<String, MessageVo>(defaultProducerFactory());
        kafkaTemplate.setProducerListener(messageProducerListener);
        return kafkaTemplate;
    }

    /*@Bean
    public NewTopic topic() {
        return TopicBuilder.name("test-top")
                .partitions(3)
                .replicas(3)
                .build();
    }*/

    private ProducerFactory<String, MessageVo> defaultProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig(JsonSerializer.class));
    }

    private Map<String, Object> producerConfig(Class<?> valueSerializerClass) {
        final Map<String, Object> properties = new HashMap<>();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass);
        return properties;
    }
}
