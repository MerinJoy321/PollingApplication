package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.response.MessageResponse;
import com.example.entity.Announcement;
import com.example.service.AnnouncementService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/announcements")
@Validated
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Operation(summary = "List announcements")
    @GetMapping
    public ResponseEntity<List<Announcement>> getAll() {
        return ResponseEntity.ok(announcementService.getAll());
    }

    @Operation(summary = "Admin creates announcement")
    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody Map<String, String> payload) {
        String title = payload.get("title");
        String message = payload.get("message");
        if (title == null || title.isBlank() || message == null || message.isBlank()) {
            throw new IllegalArgumentException("Title and message are required");
        }
        return ResponseEntity.ok(announcementService.create(title, message));
    }
}

