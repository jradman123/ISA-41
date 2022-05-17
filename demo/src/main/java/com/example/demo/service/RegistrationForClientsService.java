package com.example.demo.service;

import com.example.demo.model.users.RegisteredUser;

import java.net.UnknownHostException;

public interface RegistrationForClientsService {
    RegisteredUser sendVerificationEmail(RegisteredUser user) throws UnknownHostException;
    RegisteredUser registerClient(RegisteredUser user);
}
