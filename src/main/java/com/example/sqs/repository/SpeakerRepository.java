package com.example.sqs.repository;

import com.example.sqs.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<HistoryEntity, Long> {
}
