package com.example.service;

import com.example.dto.request.LoginRequest;
import com.example.dto.request.VoterRegisterRequest;
import com.example.dto.response.MessageResponse;
import com.example.dto.response.VoterResponse;

public interface UserServiceImpl {

    VoterResponse registerVoter(VoterRegisterRequest request);

    MessageResponse login(LoginRequest request);

    MessageResponse approveVoter(String voterId);

    
    VoterResponse getVoterByVoterId(String voterId);
}
