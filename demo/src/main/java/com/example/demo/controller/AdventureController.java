package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.mapper.AdventureMapper;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.model.Image;
import com.example.demo.model.adventures.Adventure;
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
        Adventure adventure = this.adventureService.findAdventure(id).get();
        return new ResponseEntity<>(this.adventureMapper.mapAdventureToAdventureDto(adventure),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping(value = "/add-image/{id}")
    public ResponseEntity<Adventure> addImage(@PathVariable int id,@RequestBody ImageRequest imageRequest) {
        Adventure adventure = this.adventureService.findAdventure(id).get();
        Set<Image> images = new HashSet<>();
        for (Image image: adventure.getImages()) {
            images.add(image);
        }
        images.add(this.imageMapper.mapImageRequestToImage(imageRequest));
        adventure.setImages(images);
        return new ResponseEntity<>(this.adventureService.createAdventure(adventure),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value = "/{id}/images")
    public ResponseEntity<ImagesResponse> getAdventuresImages(@PathVariable int id) {
        Adventure adventure = this.adventureService.findAdventure(id).get();
        Set<Image> images = adventure.getImages();
        ImagesResponse response = this.imageMapper.mapImagesToImagesResponse(images);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
