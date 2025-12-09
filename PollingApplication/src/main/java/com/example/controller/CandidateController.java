package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.response.CandidateResponse;
import com.example.entity.Candidate;
import com.example.service.CandidateService;

@RestController
@RequestMapping("/api/candidates")
@Validated
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/election/{electionId}")
    public ResponseEntity<List<CandidateResponse>> getApprovedByElection(@PathVariable Long electionId) {
        List<Candidate> candidates = candidateService.getCandidatesByElection(electionId);
        List<CandidateResponse> responses = candidates.stream().map(c -> {
            CandidateResponse cr = new CandidateResponse();
            cr.setId(c.getId());
            cr.setName(c.getName());
            cr.setManifesto(c.getManifesto());
            cr.setApproved(c.isApproved());
            return cr;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
