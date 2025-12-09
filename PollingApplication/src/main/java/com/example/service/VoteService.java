package com.example.service;

import com.example.dto.request.VoteRequest;
import com.example.dto.response.MessageResponse;
import com.example.entity.Election;
import com.example.entity.Voter;
import com.example.entity.Vote;
import com.example.entity.Candidate;
import com.example.repository.ElectionRepository;
import com.example.repository.VoterRepository;
import com.example.repository.VoteRepository;
import com.example.repository.CandidateRepository;
import com.example.exception.ResourceNotFoundException;
import com.example.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoteService implements VoteServiceImpl {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public MessageResponse castVote(VoteRequest request) {
        Voter voter = voterRepository.findByVoterId(request.getVoterId())
                .orElseThrow(() -> new ResourceNotFoundException("Voter not found"));

        if (!voter.isApproved()) {
            throw new IllegalArgumentException("Voter not approved");
        }

        if (voter.isHasVoted()) {
            throw new IllegalArgumentException("Voter has already voted");
        }

        Election election = electionRepository.findById(request.getElectionId())
                .orElseThrow(() -> new ResourceNotFoundException("Election not found"));

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(election.getStartTime()) || now.isAfter(election.getEndTime())) {
            throw new IllegalArgumentException("Voting not active");
        }

        Candidate candidate = candidateRepository.findById(request.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        if (candidate.getElection() == null || !candidate.getElection().getId().equals(election.getId())) {
            throw new IllegalArgumentException("Candidate not part of this election");
        }
        if (!candidate.isApproved()) {
            throw new IllegalArgumentException("Candidate not approved");
        }

        String hashedVote = HashUtil.hash(request.getCandidateId() + "|" + voter.getVoterId());

        Vote vote = new Vote();
        vote.setCandidateId(request.getCandidateId());
        vote.setElectionId(election.getId());
        vote.setHashedVote(hashedVote);
        voteRepository.save(vote);

        voter.setHasVoted(true);
        voterRepository.save(voter);

        return new MessageResponse("Vote cast successfully");
    }
}
