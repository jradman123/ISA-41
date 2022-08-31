package com.example.demo.controller;


import com.example.demo.dto.AdventureDto;
import com.example.demo.dto.InstructorAvailabilityDto;
import com.example.demo.dto.NewInstructorAvailability;
import com.example.demo.mapper.InstructorAvailabilityMapper;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.InstructorAvailability;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.InstructorAvailabilityService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor-availabilities")
public class InstructorAvailabilityController {

    @Autowired
    private InstructorAvailabilityService instructorAvailabilityService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private InstructorAvailabilityMapper instructorAvailabilityMapper;

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value="/all-for-instructor")
    public ResponseEntity<List<InstructorAvailabilityDto>> getAllForInstructor(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getEmailFromToken(token);
        List<InstructorAvailabilityDto> instructorAvailabilities = new ArrayList<>();
        for (InstructorAvailability availability : instructorAvailabilityService.getAllCurrentAndFutureForInstructor(userService.findByEmail(email).getId())) {
            instructorAvailabilities.add(instructorAvailabilityMapper.mapToInstructorAvailabilityDto(availability));
        }
        return new ResponseEntity<>(instructorAvailabilities, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping(value="/add-new-period")
    public ResponseEntity<String> addNewPeriod(@RequestBody NewInstructorAvailability newInstructorAvailability,
                                                                        HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getEmailFromToken(token);
        Instructor instructor =(Instructor) userService.findByEmail(email);
        InstructorAvailability instructorAvailability = instructorAvailabilityMapper.mapNewInstructorAvailabilityToEntity(newInstructorAvailability,instructor);
        InstructorAvailability saved = instructorAvailabilityService.addNewAvailability(instructorAvailability);
        if(saved == null) {
            return new ResponseEntity<>("\"Error!\"", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>("\"Success!\"", HttpStatus.CREATED);
        }
    }
}
