package com.example.service;

import java.util.List;

import com.example.dto.request.CandidateRegisterRequest;
import com.example.dto.response.MessageResponse;
import com.example.entity.Candidate;

public interface CandidateServiceImpl {
    MessageResponse registerCandidate(CandidateRegisterRequest request);
    MessageResponse approveCandidate(Long candidateId);
    List<Candidate> getCandidatesByElection(Long electionId);
}
