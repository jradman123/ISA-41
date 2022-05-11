package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequestViewDto;
import com.example.demo.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(value = "/approve/{email}")
    public ResponseEntity<List<RegistrationRequestViewDto>> approveRequest(@PathVariable String email) {
        registrationRequestService.approveRequest(email);
        return new ResponseEntity<>(registrationRequestService.findAllPending(), HttpStatus.OK);

    }


}
