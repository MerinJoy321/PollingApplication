package com.example.controller;

import com.example.dto.request.VoteRequest;
import com.example.dto.response.MessageResponse;
import com.example.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vote")
@Validated
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Operation(summary = "Cast a vote")
    @PostMapping("/cast")
    public ResponseEntity<MessageResponse> castVote(@Valid @RequestBody VoteRequest request) {
        MessageResponse response = voteService.castVote(request);
        return ResponseEntity.ok(response);
    }
}
