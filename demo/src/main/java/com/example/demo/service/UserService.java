package com.example.demo.service;

import com.example.demo.dto.AdministratorRegistrationDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.PersonalData;
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
    Administrator saveAdministrator(AdministratorRegistrationDto administratorRegistrationDto);
    void activateAccount(String email);
    PersonalData getPersonalData(String email);
    PersonalData updatePersonalData(PersonalData data,String email);
    void changePassword(String email, ChangePasswordDto changePasswordDto);
    boolean isFirstLogin(String email);
    boolean isPredefAdmin(String email);
    void deleteUser(User user);

    List<PersonalData> findAllUsers();
}
