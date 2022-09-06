package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.users.Administrator;
import com.example.demo.model.users.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('CottageAdvertiser') or hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/personal-data")
    public ResponseEntity<PersonalData> getPersonalData(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        return new ResponseEntity<>(userService.getPersonalData(tokenUtils.getEmailFromToken(token)),
                    HttpStatus.OK);

    }
    
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('CottageAdvertiser') or hasAuthority('ShipAdvertiser')")
    @PutMapping(value = "/update-personal-data")
    public ResponseEntity<PersonalData> updatePersonalData(@RequestBody PersonalData personalData, HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        return new ResponseEntity<>(userService.updatePersonalData(personalData,tokenUtils.getEmailFromToken(token)),
                HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Instructor') or hasAuthority('CottageAdvertiser') or hasAuthority('ShipAdvertiser')")
    @PutMapping(value = "/change-password")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody ChangePasswordDto changePasswordDto, HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        userService.changePassword(tokenUtils.getEmailFromToken(token), changePasswordDto);
        return ResponseEntity.noContent().build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/all")
    public List<PersonalData> findAll() {

       List<PersonalData> personalDatas=new ArrayList<>();
       for(User user:this.userService.findAll()) {
           personalDatas.add(new PersonalData(user));
       }
       return personalDatas;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/findByEmail/{clientEmail}")
    public PersonalData findByEmail(@PathVariable String clientEmail) {

        System.out.print("USLAAAA");
       return userService.getPersonalData(clientEmail);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/is-first-login")
    public ResponseEntity<?> isFirstLogin(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        Administrator admin = (Administrator) userService.findByEmail(tokenUtils.getEmailFromToken(token));
        System.out.println(admin.isFirstLogin());
        return new ResponseEntity<>(admin.isFirstLogin(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Instructor') or hasAuthority('CottageAdvertiser') or hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/instructor-personal-data")
    public ResponseEntity<InstructorPersonalData> getInstructorPersonalData(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        return new ResponseEntity<>(userService.getInstructorPersonalData(tokenUtils.getEmailFromToken(token)),
                HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('Instructor')")
    @PutMapping(value = "/update-instructor-personal-data")
    public ResponseEntity<InstructorPersonalData> updateInstructorPersonalData(@RequestBody InstructorPersonalData personalData, HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        return new ResponseEntity<>(userService.updateInstructorPersonalData(personalData,tokenUtils.getEmailFromToken(token)),
                HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> response = new ArrayList<>();
        for(User user : userService.getAll()){
            response.add(new UserResponse(user));
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserResponse>> delete(@PathVariable int id) {
       userService.deleteUser(id);
        List<UserResponse> response = new ArrayList<>();
        for(User user : userService.getAll()){
            response.add(new UserResponse(user));
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
