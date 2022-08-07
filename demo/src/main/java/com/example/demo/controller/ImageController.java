package com.example.demo.controller;

import com.example.demo.dto.ImageDto;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class ImageController {

    @Autowired
    ImageService imageService;



    @PostMapping(value="/addPicture")
     public String addPicture(@RequestBody ImageDto dto) {
        return this.imageService.addImage(dto);
    }






}
