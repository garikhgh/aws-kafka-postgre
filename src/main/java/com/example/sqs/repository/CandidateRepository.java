package com.example.sqs.repository;

import com.example.sqs.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    Optional<CandidateEntity> findBySpeechName(String speechName);
}
