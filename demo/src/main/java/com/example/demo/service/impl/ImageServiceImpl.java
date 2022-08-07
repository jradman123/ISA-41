package com.example.demo.service.impl;

import com.example.demo.dto.ImageDto;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;





    @Override
    public String addImage(ImageDto dto) {
        return null;
    }

    @Override
    public void addAdventureImage(Image image) {
        imageRepository.save(image);
    }

   /* @Override
    public ResponseEntity<Long> deleteImage(Long idP, Long id) {
        List<CottageImage> cottageImages=this.cottageImageRepository.findAll();
        for (CottageImage cottageImage: cottageImages)
        {
            if(cottageImage.getCottage().getId()==id & cottageImage.getId()==idP) {
                cottageImage.setDeleted(true);
                cottageImageRepository.save(cottageImage);
            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }*/

}
