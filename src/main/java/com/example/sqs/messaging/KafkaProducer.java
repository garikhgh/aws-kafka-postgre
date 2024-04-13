package com.example.sqs.messaging;

import com.example.sqs.dto.VoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private static final String NOTIFICATION_TOPIC = "vote";

    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    public void send(VoteDto message) {
        kafkaTemplate.send(NOTIFICATION_TOPIC, message);
        log.info("New Notification sending is completed.");
    }
}
