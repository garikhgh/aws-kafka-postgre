package com.example.sqs.controller;

import com.example.sqs.dto.VoteDto;
import com.example.sqs.entity.CandidateEntity;
import com.example.sqs.messaging.KafkaProducer;
import com.example.sqs.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;
    private final KafkaProducer kafkaProducer;

    @DeleteMapping("/delete/{all}")
    ResponseEntity<Void> deleteAll(@PathVariable String all) {
        candidateService.deleteAll(all);
        log.info("---------------------Deleting history---------------------");
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/add/candidate")
    ResponseEntity<CandidateEntity> addCandidate(@RequestBody CandidateEntity candidate) {
        CandidateEntity c = candidateService.addCandidate(candidate);
        return ResponseEntity.ok(c);
    }

    @PostMapping("/add/producer")
    ResponseEntity<String> addCandidateViaProducer(@RequestBody VoteDto voteDto) {
        kafkaProducer.send(voteDto);
        return ResponseEntity.ok("Vote is send to Candidate");
    }


    @PostMapping("/add/vote")
    ResponseEntity<String> addLikes(@RequestBody VoteDto vote) {

        try {
            candidateService.addVotesToCandidate(vote);
//            kafkaProducer.send(vote);
            return new ResponseEntity<>("Votes successfully added to Candidate", HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.warn("Exception in controller:", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
