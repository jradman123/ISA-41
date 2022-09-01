package com.example.demo.controller;


import com.example.demo.dto.AdventureQuickReservationDto;
import com.example.demo.dto.AdventureQuickReservationResponse;
import com.example.demo.dto.ResponseUtility;
import com.example.demo.mapper.AdventureQuickReservationMapper;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.AdventureQuickReservationService;
import com.example.demo.service.AdventureService;
import com.example.demo.service.AdventureUtilityService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/adventure-quick-reservation")
public class AdventureQuickReservationController {

    @Autowired
    private AdventureQuickReservationService adventureQuickReservationService;

    @Autowired
    private AdventureQuickReservationMapper adventureQuickReservationMapper;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private AdventureUtilityService adventureUtilityService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping()
    public ResponseEntity<String> addNewAdventureQuickReservation(@Valid @RequestBody AdventureQuickReservationDto adventureQuickReservationDto) {
        Adventure adventure = adventureService.findAdventure(Integer.parseInt(adventureQuickReservationDto.getAdventureId()));
        AdventureQuickReservation adventureQuickReservation = adventureQuickReservationMapper.mapToAdventureQuickReservation(adventureQuickReservationDto,adventure);
        Set<AdventureUtility> utilities = new HashSet<>();
        if(adventureQuickReservationDto.getUtilities() != null) {
            for (ResponseUtility responseUtility : adventureQuickReservationDto.getUtilities()) {
                utilities.add(adventureUtilityService.findById(Long.parseLong(responseUtility.getId())));
            }
        }
        adventureQuickReservation.setAdventureUtilities(utilities);
        String result = adventureQuickReservationService.addNewQuickReservation(adventureQuickReservation);
        if(result.equals("Success!")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value = "/all-quick-reservations-for-adventure/{id}")
    public ResponseEntity<List<AdventureQuickReservationResponse>> getAllForAdventure(@PathVariable int id) {
        List<AdventureQuickReservationResponse> reservations = new ArrayList<>();
        for (AdventureQuickReservation reservation : adventureQuickReservationService.findAllByAdventureId(id)) {
            reservations.add(adventureQuickReservationMapper.mapToAdventureQuickReservationResponse(reservation));
        }
        return new ResponseEntity<>(reservations,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAdventureQuickReservation(@PathVariable Long id) {
        AdventureQuickReservation reservation = adventureQuickReservationService.deleteAdventureQuickReservation(id);
        if(reservation.isDeleted()) {
            return new ResponseEntity<>("\"Success!\"", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("\"Error happened!\"", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value = "/all-for-instructor")
    public ResponseEntity<List<AdventureQuickReservationResponse>> findAllForInstructor(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        int id = userService.findByEmail(tokenUtils.getEmailFromToken(token)).getId();
        List<AdventureQuickReservationResponse> reservations = new ArrayList<>();
        for (AdventureQuickReservation reservation : adventureQuickReservationService.findAllForInstructor(id)) {
            reservations.add(adventureQuickReservationMapper.mapToAdventureQuickReservationResponse(reservation));
        }
        return new ResponseEntity<>(reservations,HttpStatus.OK);
    }
}
