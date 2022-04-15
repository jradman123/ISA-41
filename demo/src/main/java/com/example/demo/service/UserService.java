package com.example.demo.service;

import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findByEmail(String email);
    User save(User user);
    Instructor saveInstructor(RegistrationRequestDto request);
}
