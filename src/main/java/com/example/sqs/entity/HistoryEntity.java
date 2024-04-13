package com.example.sqs.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history")
@Builder
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "candidateName")
    private String candidateName;

    private int votes;

    private String status;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime created;
}
