package com.example.demo.controller;


import com.example.demo.dto.AdventureQuickReservationDto;
import com.example.demo.dto.ResponseUtility;
import com.example.demo.mapper.AdventureQuickReservationMapper;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.service.AdventureQuickReservationService;
import com.example.demo.service.AdventureService;
import com.example.demo.service.AdventureUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
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

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping()
    public ResponseEntity<AdventureQuickReservation> addNewAdventureQuickReservation(@Valid @RequestBody AdventureQuickReservationDto adventureQuickReservationDto) {
        Adventure adventure = adventureService.findAdventure(Integer.parseInt(adventureQuickReservationDto.getAdventureId()));
        AdventureQuickReservation adventureQuickReservation = adventureQuickReservationMapper.mapToAdventureQuickReservation(adventureQuickReservationDto,adventure);
        Set<AdventureUtility> utilities = new HashSet<>();
        for (ResponseUtility responseUtility : adventureQuickReservationDto.getUtilities()) {
            utilities.add(adventureUtilityService.findById(Long.parseLong(responseUtility.getId())));
        }
        adventureQuickReservation.setAdventureUtilities(utilities);
        return new ResponseEntity<>(adventureQuickReservationService.addNewQuickReservation(adventureQuickReservation), HttpStatus.CREATED);
    }
}
