package com.example.service;

import com.example.dto.request.LoginRequest;
import com.example.dto.request.VoterRegisterRequest;
import com.example.dto.response.MessageResponse;
import com.example.dto.response.VoterResponse;
import com.example.entity.Voter;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.VoterRepository;
import com.example.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserServiceImpl {

	@Autowired
    private VoterRepository voterRepository;

    @Override
    public VoterResponse registerVoter(VoterRegisterRequest request) {
        if (voterRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        Voter voter = new Voter();
        voter.setName(request.getName());
        voter.setEmail(request.getEmail());
        voter.setPassword(HashUtil.hash(request.getPassword()));
        voterRepository.save(voter);

        VoterResponse response = new VoterResponse();
        response.setName(voter.getName());
        response.setEmail(voter.getEmail());
        response.setApproved(voter.isApproved());
        return response;
    }

    @Override
    public MessageResponse login(LoginRequest request) {
        Voter voter = voterRepository.findByVoterId(request.getVoterId())
                .orElseThrow(() -> new ResourceNotFoundException("Voter not found"));

        if (!HashUtil.check(request.getPassword(), voter.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return new MessageResponse("Login successful");
    }

    @Override
    public MessageResponse approveVoter(String voterId) {
        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow(() -> new ResourceNotFoundException("Voter not found"));

        voter.setApproved(true);
        voter.setVoterId(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        voterRepository.save(voter);

        return new MessageResponse("Voter approved with voter ID: " + voter.getVoterId());
    }

    @Override
    public VoterResponse getVoterByVoterId(String voterId) {
        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow(() -> new ResourceNotFoundException("Voter not found"));

        VoterResponse response = new VoterResponse();
        response.setVoterId(voter.getVoterId());
        response.setName(voter.getName());
        response.setApproved(voter.isApproved());
        response.setHasVoted(voter.isHasVoted());
        return response;
    }
}