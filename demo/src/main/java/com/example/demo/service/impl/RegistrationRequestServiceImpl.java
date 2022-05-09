package com.example.demo.service.impl;

import com.example.demo.model.users.RegistrationRequest;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.service.RegistrationRequestService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<RegistrationRequest> findAll() {
        return registrationRequestRepository.findAll();
    }

    @Override
    public List<RegistrationRequest> findAllAllowed() {
        return registrationRequestRepository.findAllAllowed();
    }

    @Override
    public List<RegistrationRequest> findAllNotAllowed() {
        return registrationRequestRepository.findAllNotAllowed();
    }

    @Override
    public void approveRequest(String email) {
        RegistrationRequest request = registrationRequestRepository.findByEmail(email);
        request.setAllowed(true);
        registrationRequestRepository.save(request);
        userService.activateAccount(email);
    }

    @Override
    public void rejectRequest(String email) {
        RegistrationRequest request = registrationRequestRepository.findByEmail(email);
        request.setAllowed(false);
        registrationRequestRepository.save(request);
    }
}
