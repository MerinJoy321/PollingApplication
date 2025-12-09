package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VoteRequest {

    @NotBlank(message = "Voter ID is required")
    private String voterId;

    @NotNull(message = "Election ID is required")
    private Long electionId;

    @NotNull(message = "Candidate ID is required")
    private Long candidateId;

    // Getters & Setters
    public String getVoterId() { return voterId; }
    public void setVoterId(String voterId) { this.voterId = voterId; }

    public Long getElectionId() { return electionId; }
    public void setElectionId(Long electionId) { this.electionId = electionId; }

    public Long getCandidateId() { return candidateId; }
    public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }
}
