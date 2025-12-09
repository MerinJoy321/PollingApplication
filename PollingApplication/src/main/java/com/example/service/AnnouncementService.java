package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.response.MessageResponse;
import com.example.entity.Announcement;
import com.example.repository.AnnouncementRepository;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAll() {
        return announcementRepository.findAllByOrderByTimestampDesc();
    }

    public MessageResponse create(String title, String message) {
        Announcement announcement = new Announcement(title, message);
        announcementRepository.save(announcement);
        return new MessageResponse("Announcement published");
    }
}

