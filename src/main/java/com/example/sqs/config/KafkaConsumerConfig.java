package com.example.sqs.config;

import com.example.sqs.dto.VoteDto;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, VoteDto> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "consuming");

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        Map<String, Class<?>> classMap = new HashMap<>();
        typeMapper.setIdClassMapping(classMap);
        typeMapper.addTrustedPackages("*");

        JsonDeserializer<VoteDto> jsonDeserializer = new JsonDeserializer<>(VoteDto.class);
        jsonDeserializer.setTypeMapper(typeMapper);
        jsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, VoteDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, VoteDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        log.info("Configure concurrent consumer Kafka");
        return factory;
    }

}
