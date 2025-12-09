package com.example.service;

import com.example.dto.request.CreateElectionRequest;
import com.example.dto.response.ElectionResultResponse;
import com.example.dto.response.MessageResponse;
import com.example.entity.Candidate;
import com.example.entity.Election;
import com.example.repository.CandidateRepository;
import com.example.repository.ElectionRepository;
import com.example.repository.VoteRepository;
import com.example.repository.VoterRepository;
import com.example.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectionService implements ElectionServiceImpl {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Override
    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    @Override
    public MessageResponse createElection(CreateElectionRequest request) {
        Election election = new Election();
        election.setTitle(request.getTitle());
        election.setStartTime(request.getStartTime());
        election.setEndTime(request.getEndTime());
        electionRepository.save(election);

        return new MessageResponse("Election created successfully");
    }

    @Override
    public ElectionResultResponse getElectionDetails(Long electionId) {
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ResourceNotFoundException("Election not found"));

        List<Candidate> candidates = candidateRepository.findByElection(election);

        Map<String, Integer> voteCount = new HashMap<>();
        for (Candidate c : candidates) {
            int count = voteRepository.findByCandidateId(c.getId()).size();
            voteCount.put(c.getName(), count);
        }

        ElectionResultResponse response = new ElectionResultResponse();
        response.setElectionTitle(election.getTitle());
        response.setVoteCountSummary(voteCount);

        if (election.getWinnerCandidateId() != null) {
            Candidate winner = candidateRepository.findById(election.getWinnerCandidateId())
                    .orElse(null);
            if (winner != null) {
                response.setWinnerName(winner.getName());
            }
        }

        return response;
    }
    public Election getActiveElection() {
        return electionRepository.findFirstByOrderByStartTimeDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active election found"));
    }

    @Override
    public double getPollingPercentage(Long electionId) {
        // total approved voters for denominator
        long totalApproved = voterRepository.countByApprovedTrue();
        if (totalApproved == 0) {
            return 0.0;
        }

        long votesCast = voteRepository.countByElectionId(electionId);
        double percentage = (votesCast * 100.0) / totalApproved;
        // Cap at 100 to avoid over-reporting
        return Math.min(percentage, 100.0);
    }

    @Override
    public Election getElectionDetailsEntity(Long electionId) {
        return electionRepository.findById(electionId)
                .orElseThrow(() -> new ResourceNotFoundException("Election not found"));
    }

    @Override
    public MessageResponse declareResults(Long electionId) {
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ResourceNotFoundException("Election not found"));

        List<Candidate> candidates = candidateRepository.findByElection(election);

        Candidate winner = candidates.stream()
                .max(Comparator.comparingInt(c -> voteRepository.findByCandidateId(c.getId()).size()))
                .orElse(null);

        if (winner != null) {
            election.setWinnerCandidateId(winner.getId());
        }

        election.setResultsDeclared(true);
        electionRepository.save(election);

        voteRepository.deleteByElectionId(electionId);

        return new MessageResponse("Results declared and votes deleted");
    }
}
