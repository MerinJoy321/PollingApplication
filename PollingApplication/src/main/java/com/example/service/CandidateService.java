package com.example.service;

import com.example.dto.request.CandidateRegisterRequest;
import com.example.dto.response.MessageResponse;
import com.example.entity.Candidate;
import com.example.entity.Election;
import com.example.entity.Voter;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CandidateRepository;
import com.example.repository.ElectionRepository;
import com.example.repository.VoterRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService implements CandidateServiceImpl {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Override
    public MessageResponse registerCandidate(CandidateRegisterRequest request) {
        Voter voter = voterRepository.findByVoterId(request.getVoterId())
                .orElseThrow(() -> new ResourceNotFoundException("Voter not found"));

        if (candidateRepository.existsByVoterVoterId(voter.getVoterId())) {
            throw new IllegalArgumentException("Already registered as candidate");
        }

        Election election = electionRepository.findById(request.getElectionId())
                .orElseThrow(() -> new ResourceNotFoundException("Election not found"));

        Candidate candidate = new Candidate();
        candidate.setVoter(voter);
        candidate.setName(voter.getName());
        candidate.setManifesto(request.getManifesto());
        candidate.setApproved(false);
        candidate.setElection(election);
        candidateRepository.save(candidate);

        return new MessageResponse("Candidate registration submitted for approval");
    }

    @Override
    public MessageResponse approveCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        candidate.setApproved(true);
        candidateRepository.save(candidate);

        return new MessageResponse("Candidate approved");
    }
    @Override
    public List<Candidate> getCandidatesByElection(Long electionId) {
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ResourceNotFoundException("Election not found"));

        return candidateRepository.findByElectionAndApprovedTrue(election);
    }
}
