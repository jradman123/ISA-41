package com.example.demo.service;

import com.example.demo.model.users.RegisteredUser;
import com.example.demo.model.users.User;

import java.net.UnknownHostException;

public interface RegistrationForClientsService {
    RegisteredUser sendVerificationEmail(RegisteredUser user) throws UnknownHostException;
    User registerClient(User user);
}
