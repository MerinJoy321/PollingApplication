package com.example.service;

import java.util.List;

import com.example.dto.request.CreateElectionRequest;
import com.example.dto.response.ElectionResultResponse;
import com.example.dto.response.MessageResponse;
import com.example.entity.Election;

public interface ElectionServiceImpl {

    MessageResponse createElection(CreateElectionRequest request);

    ElectionResultResponse getElectionDetails(Long electionId);

    MessageResponse declareResults(Long electionId);

	List<Election> getAllElections();

    double getPollingPercentage(Long electionId);

    Election getElectionDetailsEntity(Long electionId);
}
