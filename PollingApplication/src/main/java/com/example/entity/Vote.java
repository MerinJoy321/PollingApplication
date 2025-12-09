package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long candidateId; // reference to Candidate

    @NotNull
    private Long electionId; // reference to Election

    @NotNull
    @Column(length = 512)
    private String hashedVote; // hashed ballot data

    private LocalDateTime timestamp = LocalDateTime.now();

    // Constructors
    public Vote() {}

    public Vote(Long candidateId, Long electionId, String hashedVote) {
        this.candidateId = candidateId;
        this.electionId = electionId;
        this.hashedVote = hashedVote;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public Long getCandidateId() { return candidateId; }
    public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }
    public Long getElectionId() { return electionId; }
    public void setElectionId(Long electionId) { this.electionId = electionId; }
    public String getHashedVote() { return hashedVote; }
    public void setHashedVote(String hashedVote) { this.hashedVote = hashedVote; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
