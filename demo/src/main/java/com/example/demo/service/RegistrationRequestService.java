package com.example.demo.service;

import com.example.demo.model.users.RegistrationRequest;

import java.util.List;

public interface RegistrationRequestService {
    List<RegistrationRequest> findAll();
    List<RegistrationRequest> findAllAllowed();
    List<RegistrationRequest> findAllNotAllowed();
    void approveRequest(String email);
    void rejectRequest(String email);

}
