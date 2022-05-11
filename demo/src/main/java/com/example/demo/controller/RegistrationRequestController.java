package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequestViewDto;
import com.example.demo.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(value = "/approve/{email}")
    public ResponseEntity<List<RegistrationRequestViewDto>> approveRequest(@PathVariable String email) {
        registrationRequestService.approveRequest(email);
        return new ResponseEntity<>(registrationRequestService.findAll(), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(value = "/reject/{email}")
    public ResponseEntity<List<RegistrationRequestViewDto>> rejectRequest(@PathVariable String email, @RequestBody String reason) {
        registrationRequestService.rejectRequest(email,reason);
        return new ResponseEntity<>(registrationRequestService.findAll(), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<RegistrationRequestViewDto>> getAll() {
        return new ResponseEntity<>(registrationRequestService.findAll(), HttpStatus.OK);

    }


}
