package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.mapper.*;
import com.example.demo.model.Image;
import com.example.demo.model.Utility;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureRule;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.model.adventures.FishingEquipment;
import com.example.demo.model.users.Instructor;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.AdventureService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/adventures")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AdventureMapper adventureMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private AdventureRuleMapper adventureRuleMapper;

    @Autowired
    private FishingEquipmentMapper fishingEquipmentMapper;

    @Autowired
    private AdventureUtilityMapper adventureUtilityMapper;

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
        Adventure adventure = adventureMapper.mapNewAdventureDtoToAdventure(newAdventureDto);
        adventure.setInstructor(instructor);
        return new ResponseEntity<>(adventureService.createAdventure(adventure), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/find-adventure/{id}")
    public ResponseEntity<AdventureDto> findAdventure(@PathVariable int id) {
        Adventure adventure = this.adventureService.findAdventure(id);
        return new ResponseEntity<>(this.adventureMapper.mapAdventureToAdventureDto(adventure),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping(value = "/add-image/{id}")
    public ResponseEntity<Adventure> addImage(@PathVariable int id,@RequestBody ImageRequest imageRequest) {
        Adventure adventure = this.adventureService.findAdventure(id);
        Set<Image> images = new HashSet<>();
        for (Image image: adventure.getImages()) {
            images.add(image);
        }
        images.add(this.imageMapper.mapImageRequestToImage(imageRequest));
        adventure.setImages(images);
        return new ResponseEntity<>(this.adventureService.createAdventure(adventure),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/{id}/images")
    public ResponseEntity<ImagesResponse> getAdventuresImages(@PathVariable int id) {
        Adventure adventure = this.adventureService.findAdventure(id);
        Set<Image> images = adventure.getImages();
        ImagesResponse response = this.imageMapper.mapImagesToImagesResponse(images);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @PutMapping(value = "/{id}/update")
    public ResponseEntity<AdventureDto> updateAdventure(@PathVariable int id,@RequestBody AdventureDto adventureForUpdate) {
        Adventure adventure = this.adventureMapper.mapAdventureDtoToAdventure(adventureForUpdate);
        Adventure updated = this.adventureService.updateAdventure(adventure,id);
        return new ResponseEntity<>(this.adventureMapper.mapAdventureToAdventureDto(updated),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/{id}/rules")
    public ResponseEntity<Set<ResponseRules>> getAdventureRules(@PathVariable int id) {
        Adventure adventure = this.adventureService.findAdventure(id);
        Set<AdventureRule> adventureRules = this.adventureService.getRulesByAdventure(adventure);
        return new ResponseEntity<>(this.adventureRuleMapper.mapRulesToResponseRules(adventureRules),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/{id}/fishing-equipments")
    public ResponseEntity<Set<ResponseFishingEquipment>> getFishingEquipments(@PathVariable int id) {
        Adventure adventure = this.adventureService.findAdventure(id);
        Set<FishingEquipment> adventureFishingEquipments = this.adventureService.getFishingEquipmentByAdventure(adventure);
        return new ResponseEntity<>(this.fishingEquipmentMapper.mapFishingEquipmentsToResponseFishingEquipments(adventureFishingEquipments),
                                    HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/{id}/utilities")
    public ResponseEntity<Set<ResponseUtility>> getUtilities(@PathVariable int id) {
        Adventure adventure = this.adventureService.findAdventure(id);
        Set<AdventureUtility> adventureUtilities = this.adventureService.getUtilitiesByAdventure(adventure);
        return new ResponseEntity<>(this.adventureUtilityMapper.mapAdventureUtilityToResponseUtility(adventureUtilities),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAdventure(@PathVariable int id) {
        Adventure adventure = this.adventureService.deleteAdventure(id);
        if(adventure.isDeleted()){
            return new ResponseEntity<>("\"Success!\"",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("\"Error!\"",HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value = "/{id}/average-rating")
    public ResponseEntity<AdventureAverageRating> getAdventureAverageRating(@PathVariable int id) {
        return new ResponseEntity<>(adventureService.getRatingForAdventure(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<List<AdventureDto>> getAll() {
        List<AdventureDto> adventureDtos = new ArrayList<>();
        for (Adventure adventure : adventureService.getAllUndeleted()) {
            adventureDtos.add(adventureMapper.mapAdventureToAdventureDto(adventure));
        }
        return new ResponseEntity<>(adventureDtos, HttpStatus.OK);
    }

}
