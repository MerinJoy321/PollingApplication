package com.example.controller;

import com.example.dto.response.ElectionResultResponse;
import com.example.service.ElectionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/election")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @Operation(summary = "Get details of an election by ID")
    @GetMapping("/{electionId}")
    public ResponseEntity<ElectionResultResponse> getElectionDetails(@PathVariable Long electionId) {
        ElectionResultResponse response = electionService.getElectionDetails(electionId);
        return ResponseEntity.ok(response);
    }
}
