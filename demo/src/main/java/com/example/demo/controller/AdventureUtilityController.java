package com.example.demo.controller;

import com.example.demo.dto.AdventureUtilityDto;
import com.example.demo.dto.ResponseUtility;
import com.example.demo.mapper.AdventureUtilityMapper;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.service.AdventureService;
import com.example.demo.service.AdventureUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/adventure-utilities")
public class AdventureUtilityController {

    @Autowired
    private AdventureUtilityService adventureUtilityService;

    @Autowired
    private AdventureUtilityMapper adventureUtilityMapper;

    @Autowired
    private AdventureService adventureService;

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping(value = "")
    public ResponseEntity<Set<ResponseUtility>> addAdventureUtility(@RequestBody AdventureUtilityDto adventureUtilityDto) {
        AdventureUtility utility = this.adventureUtilityMapper.mapAdventureUtilityDtoToAdventureUtility(adventureUtilityDto);
        AdventureUtility savedUtility = this.adventureUtilityService.createUtility(utility);
        Adventure updated = adventureService.addUtility(Integer.parseInt(adventureUtilityDto.getAdventureId()),savedUtility);
        return new ResponseEntity<>(this.adventureUtilityMapper.mapAdventureUtilityToResponseUtility(updated.getUtilities()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AdventureUtility> deleteAdventureRule(@PathVariable Long id) {
        return new ResponseEntity<>(adventureUtilityService.deleteById(id),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AdventureUtility> updateAdventureUtility(@PathVariable Long id,@RequestBody AdventureUtilityDto adventureUtilityDto) {
        AdventureUtility adventureUtility = adventureUtilityMapper.mapAdventureUtilityDtoToAdventureUtility(adventureUtilityDto);
        return new ResponseEntity<>(adventureUtilityService.updateAdventureUtility(adventureUtility,id),HttpStatus.OK);
    }
}
