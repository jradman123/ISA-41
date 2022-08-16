package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.model.Image;
import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.Room;
import com.example.demo.repository.CottageRepository;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.impl.CottageServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cottages")
public class CottageController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CottageServiceImpl cottageService;
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private CottageRepository cottageRepository;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/all")
    public List<CottageDto> findAll() {
        return this.cottageService.findAll();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    //ovo samo moze da radi vlasnik vikendice
    @PostMapping(value = "/createCottage")
    public Cottage createCottage(@RequestBody CottageDto newCottage) {
        return this.cottageService.createCottage(newCottage);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    //isto radi vlasnik vikendice
    @GetMapping(value = "/findCottage/{id}")
    public CottageDto findCottage(@PathVariable Long id) {
        return this.cottageService.findCottage(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    //isto radi vlasnik vikendice
    @GetMapping(value = "/findRoomsById/{id}")
    public List<RoomDto> findRoomsByCotttage(@PathVariable Long id) {
        return this.cottageService.findRoomsByCottage(id);
    }



    @CrossOrigin(origins = "http://localhost:4200")
    //radi samo vlasnik vikendice
    @DeleteMapping(value = "/deleteRoomByCottage/{id}/{idCottage}")
    public ResponseEntity<Long> deleteRoomByCottage(@PathVariable Long id,@PathVariable Long idCottage) {
        return this.cottageService.deleteRoomByCottage(id,idCottage);
    }


    @GetMapping(value = "/search")
    public List<CottageDto> search(CottageDto dto) {
        return this.cottageService.search(dto);
    }



    @CrossOrigin(origins = "http://localhost:4200")
    //ovo samo moze da radi vlasnik vikendice
    @PostMapping(value = "/createRoomByCottage")
    public Room createRoom(@RequestBody RoomDto newRoom) {
        return this.cottageService.createRoom(newRoom);
    }









    @CrossOrigin(origins = "http://localhost:4200")
    //radi samo vlasnik vikendice
    @DeleteMapping(value = "/deleteCottage/{id}")
    public ResponseEntity<Long> deleteCottage(@PathVariable Long id) {
        return this.cottageService.deleteCottage(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    //isto radi vlasnik vikendice
    @GetMapping(value="/findOwnerCottages/{email}")
    public List<CottageDto> findCottagesFromOwner(@PathVariable String email) {
        return this.cottageService.getOwnerCottages(email);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @PutMapping(value = "/editCottage")
    public CottageDto editCottage(@RequestBody CottageDto newCottage) {
        return this.cottageService.editCottage(newCottage);

    }


    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @PostMapping(value = "/add-image/{id}")
    public ResponseEntity<Cottage> addImage(@PathVariable Long id,@RequestBody ImageRequest imageRequest) {
        Cottage cottage = this.cottageService.findCottageById(id);
        CottageDto cottageDto = this.cottageService.findCottage(id);

         Set<Image> images = new HashSet<>();
        for (Image image:  cottage.getImages()) {
            images.add(image);
        }
        images.add(this.imageMapper.mapImageRequestToImage(imageRequest));
        cottage.setImages(images);
        return new ResponseEntity<>(this.cottageService.create(cottage)
      , HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @GetMapping(value = "/{id}/images")
    public ResponseEntity<ImagesResponse> getCottageImages(@PathVariable Long id) {
        Cottage cottage = this.cottageService.findCottageById(id);
        Set<Image> images = cottage.getImages();
        ImagesResponse response = this.imageMapper.mapImagesToImagesResponse(images);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
