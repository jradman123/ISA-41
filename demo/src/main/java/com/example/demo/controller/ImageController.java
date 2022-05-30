package com.example.demo.controller;

import com.example.demo.dto.ImageDto;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping(value="/getPicturesByCottage/{id}")
    public List<ImageDto> getPicturesByCottage(@PathVariable Long id) {
        return this.imageService.getPicturesByCottage(id);
    }

    @GetMapping(value="/getPicturesByBoat/{id}")
    public List<ImageDto> getPictureByBoat(@PathVariable Long id) {
        return this.imageService.getPicturesByBoat(id);
    }


    @PostMapping(value="/addPicture")
     public String addPicture(@RequestBody ImageDto dto) {
        return this.imageService.addImage(dto);
    }

    @DeleteMapping(value="/deletePicture/{path}")
    public ResponseEntity<Long> deletePicture(@PathVariable String path) {
        return this.imageService.deleteImage(path);
    }



}
