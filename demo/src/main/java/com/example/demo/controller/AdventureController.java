package com.example.demo.controller;

import com.example.demo.dto.AdventureDto;
import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.dto.NewAdventureDto;
import com.example.demo.mapper.AdventureMapper;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.users.Instructor;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.AdventureService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/adventures",produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AdventureMapper adventureMapper;

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value="/all-for-instructor")
    public ResponseEntity<List<AdventureDto>> getAllForInstructor(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getEmailFromToken(token);
        List<AdventureDto> adventureDtos = new ArrayList<>();
        for (Adventure adventure : adventureService.getAllForInstructor(userService.findByEmail(email).getId())) {
            adventureDtos.add(adventureMapper.mapAdventureToAdventureDto(adventure));
        }
        return new ResponseEntity<>(adventureDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping()
    public ResponseEntity<Adventure> createNewAdventure(@Valid @RequestBody NewAdventureDto newAdventureDto, HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        Instructor instructor =(Instructor) userService.findByEmail(tokenUtils.getEmailFromToken(token));
        Adventure adventure = adventureMapper.mapAdventureDtoToAdventure(newAdventureDto);
        adventure.setInstructor(instructor);
        return new ResponseEntity<>(adventureService.createAdventure(adventure), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value = "/find-adventure/{id}")
    public ResponseEntity<AdventureDto> findAdventure(@PathVariable int id) {
        Optional<Adventure> adventure = this.adventureService.findAdventure(id);
        return new ResponseEntity<>(this.adventureMapper.mapOptionalAdventureToAdventureDto(adventure),HttpStatus.OK);
    }
}
