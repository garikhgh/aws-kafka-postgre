package com.example.sqs.service;

import com.example.sqs.dto.VoteDto;
import com.example.sqs.entity.CandidateEntity;
import com.example.sqs.entity.HistoryEntity;
import com.example.sqs.repository.CandidateRepository;
import com.example.sqs.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final HistoryRepository historyRepository;

    public void deleteAll(String all) {
        if (all.equals("all")) {
            candidateRepository.deleteAll();
            historyRepository.deleteAll();
            log.info("All history is cleared");
        }

    }

    @Transactional
    public void addVotesToCandidate(VoteDto voteDto) {
        Optional<CandidateEntity> bySpeechName = candidateRepository.findBySpeechName(voteDto.getCandidateName());
        bySpeechName.ifPresentOrElse(candidate -> {
                    candidate.setVotes(candidate.getVotes() + voteDto.getVotes());
                    saveMessageToHistory(voteDto, "RECEIVED");
                    candidateRepository.save(candidate);
                    log.info("{} vote is added to {}", voteDto.getVotes(), candidate.getFirstName() + " " + candidate.getLastName());
                },
                () -> log.warn("Could not find Candidate with SpeechName={}", voteDto.getCandidateName())
        );
    }

    private void saveMessageToHistory(VoteDto voteDto, String status) {
        try {
            HistoryEntity voteHistory = HistoryEntity.builder()
                    .candidateName(voteDto.getCandidateName())
                    .votes(voteDto.getVotes())
                    .status(status)
                    .build();
            HistoryEntity savedHistory = historyRepository.save(voteHistory);
            log.warn("Saved message to history with id{}.", savedHistory.getId());
        } catch (RuntimeException ex) {
            log.warn("Failed to save message to history.", ex);
        }

    }

    public CandidateEntity addCandidate(CandidateEntity candidate) {
        return candidateRepository.save(candidate);
    }
}
