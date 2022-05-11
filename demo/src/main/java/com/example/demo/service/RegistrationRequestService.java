package com.example.demo.service;

import com.example.demo.dto.RegistrationRequestViewDto;
import com.example.demo.model.users.RegistrationRequest;

import java.util.List;

public interface RegistrationRequestService {
    List<RegistrationRequestViewDto> findAll();
    void approveRequest(String email);
    void rejectRequest(String email,String reason);

}
