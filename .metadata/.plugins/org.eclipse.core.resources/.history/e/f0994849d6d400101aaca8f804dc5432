package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "elections")
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean active = true;

    private boolean resultsDeclared = false;

    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<Candidate> candidates;

    private Long winnerCandidateId; // stores winner candidate id

    // Constructors
    public Election() {}

    public Election(String title, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public boolean isResultsDeclared() { return resultsDeclared; }
    public void setResultsDeclared(boolean resultsDeclared) { this.resultsDeclared = resultsDeclared; }
    public List<Candidate> getCandidates() { return candidates; }
    public void setCandidates(List<Candidate> candidates) { this.candidates = candidates; }
    public Long getWinnerCandidateId() { return winnerCandidateId; }
    public void setWinnerCandidateId(Long winnerCandidateId) { this.winnerCandidateId = winnerCandidateId; }
}
