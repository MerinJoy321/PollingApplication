package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.dto.request.AdminLoginRequest;
import com.example.dto.response.MessageResponse;

@Service
public class AdminService {

    @Value("${app.admin.username:admin}")
    private String adminUsername;

    @Value("${app.admin.password:admin123}")
    private String adminPassword;

    public MessageResponse login(AdminLoginRequest request) {
        if (!adminUsername.equals(request.getUsername()) || !adminPassword.equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid admin credentials");
        }
        return new MessageResponse("Admin login successful");
    }
}

