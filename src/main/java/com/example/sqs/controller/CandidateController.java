package com.example.sqs.controller;

import com.example.sqs.dto.VoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CandidateController {

    @PostMapping("/add/like")
    ResponseEntity<String> addLikes(@RequestBody VoteDto vote) {

        return ResponseEntity.ok("ok");
    }
}
