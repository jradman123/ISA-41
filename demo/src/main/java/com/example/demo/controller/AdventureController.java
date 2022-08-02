package com.example.demo.controller;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/adventures",produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value="/get-all-for-instructor")
    public ResponseEntity<List<Adventure>> getAllForInstructor(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getEmailFromToken(token);
        return new ResponseEntity<>(adventureService.getAllForInstructor(userRepository.findByEmail(email).getId()), HttpStatus.OK);
    }
}
