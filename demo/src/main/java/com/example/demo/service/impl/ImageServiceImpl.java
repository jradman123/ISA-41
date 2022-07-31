package com.example.demo.service.impl;

import com.example.demo.dto.ImageDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.model.Image;
import com.example.demo.model.Rules;
import com.example.demo.model.Utility;
import com.example.demo.model.cottages.CottageImage;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.repository.CottageImageRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CottageImageRepository cottageImageRepository;



    @Override
    public List<ImageDto> getPicturesByCottage(Long id) {

        List<ImageDto> imageDtos=new ArrayList<>();
        for(CottageImage cottageImage: cottageImageRepository.findAll()) {
            if(id.equals(cottageImage.getCottage().getId()) & cottageImage.isDeleted()==false) {
                for(Image image:imageRepository.findAll()) {
                    if(cottageImage.getImage().getId().equals(image.getId())) {
                        imageDtos.add(new ImageDto(image));
                    }
                }

            }
        }

        return imageDtos;
    }

    @Override
    public List<ImageDto> getPicturesByBoat(Long id) {
        return null;
    }

    @Override
    public String addImage(ImageDto dto) {
        return null;
    }

    @Override
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
    }

}
