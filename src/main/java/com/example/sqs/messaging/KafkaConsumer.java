package com.example.sqs.messaging;

import com.example.sqs.dto.VoteDto;
import com.example.sqs.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CandidateService candidateService;


    @KafkaListener(topics = "vote", containerFactory = "kafkaListenerContainerFactory")
    public void voteListener(VoteDto voteDto) {
        try {
            candidateService.addVotesToCandidate(voteDto);
            log.info("New vote is added to Candidate.");
        } catch (Exception e) {
            log.warn("Exception happened {}", e.getMessage());
        }
    }
}
