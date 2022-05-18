package com.example.demo.service;

import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.model.users.*;

import java.net.UnknownHostException;
import java.util.List;

public interface UserService {

    List<User> findAll();
    User findByEmail(String email);
    User save(User user);
    Instructor saveInstructor(RegistrationRequestDto request);
    ShipOwner saveShipOwner(RegistrationRequestDto userRequest);
    CottageOwner saveCottageOwner(RegistrationRequestDto userRequest);
    RegisteredUser saveRegisteredUser(RegistrationRequestDto userRequest) throws UnknownHostException;
    void activateAccount(String email);
}
