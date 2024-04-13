package com.example.sqs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate")
public class CandidateEntity {

    @Id
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "speechName")
    private String speechName;

    private int votes;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated;
}
