package com.example.demo.controller;

import com.example.demo.dto.AdministratorRegistrationDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.PersonalData;
import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.model.users.Administrator;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;



    @GetMapping(value = "/getPersonalData")
    public ResponseEntity<PersonalData> getPersonalData(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        return new ResponseEntity<>(userService.getPersonalData(tokenUtils.getEmailFromToken(token)),
                    HttpStatus.OK);

    }
    

    @PutMapping(value = "/updatePersonalData")
    public ResponseEntity<PersonalData> updatePersonalData(@RequestBody PersonalData personalData, HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        return new ResponseEntity<>(userService.updatePersonalData(personalData,tokenUtils.getEmailFromToken(token)),
                HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")

    @PutMapping(value = "/changePassword")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody ChangePasswordDto changePasswordDto, HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        userService.changePassword(tokenUtils.getEmailFromToken(token), changePasswordDto);
        return ResponseEntity.noContent().build();
    }


}
