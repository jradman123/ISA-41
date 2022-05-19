package com.example.demo.service;

import com.example.demo.dto.RegistrationRequestViewDto;
import com.example.demo.dto.RequestForDeletingAccountDto;
import com.example.demo.model.users.RequestForDeletingAccount;

import java.util.List;

public interface RequestForDeletingAccountService {

    List<RequestForDeletingAccountDto> findAll();
    void deleteAccount(String email,String response);
    void rejectDeleting(String email,String response);
    void deleteRequest(RequestForDeletingAccount request);
}
