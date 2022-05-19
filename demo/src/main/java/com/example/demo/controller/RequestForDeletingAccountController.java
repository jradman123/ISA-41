package com.example.demo.controller;

import com.example.demo.dto.RequestForDeletingAccountDto;
import com.example.demo.service.RequestForDeletingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class RequestForDeletingAccountController {

    @Autowired
    private RequestForDeletingAccountService requestForDeletingAccountService;

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(value = "/delete/{email}")
    public ResponseEntity<List<RequestForDeletingAccountDto>> deleteAccount(@PathVariable String email, @RequestBody String response) {
        requestForDeletingAccountService.deleteAccount(email,response);
        return new ResponseEntity<>(requestForDeletingAccountService.findAll(), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(value = "/reject/{email}")
    public ResponseEntity<List<RequestForDeletingAccountDto>> rejectDeleting(@PathVariable String email, @RequestBody String reason) {
        requestForDeletingAccountService.rejectDeleting(email,reason);
        return new ResponseEntity<>(requestForDeletingAccountService.findAll(), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<RequestForDeletingAccountDto>> getAll() {
        return new ResponseEntity<>(requestForDeletingAccountService.findAll(), HttpStatus.OK);

    }

}
