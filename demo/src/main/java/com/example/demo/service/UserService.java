package com.example.demo.service;

import com.example.demo.dto.PersonalData;
import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.model.users.*;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findByEmail(String email);
    User save(User user);
    Instructor saveInstructor(RegistrationRequestDto request);
    ShipOwner saveShipOwner(RegistrationRequestDto userRequest);
    CottageOwner saveCottageOwner(RegistrationRequestDto userRequest);
    void activateAccount(String email);
    PersonalData getPersonalData(String email);
    PersonalData updatePersonalData(PersonalData data,String email);
}
