package com.example.demo.service;

import com.example.demo.dto.ImageDto;
import com.example.demo.model.Image;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImageService {
    List<ImageDto> getPicturesByCottage(Long id);

    List<ImageDto> getPicturesByBoat(Long id);

    String addImage(ImageDto dto);
    void addAdventureImage(Image image);

    ResponseEntity<Long> deleteImage(Long idP, Long id);
}
