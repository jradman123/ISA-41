package com.example.demo.controller;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.ImageRequest;
import com.example.demo.dto.ImagesResponse;
import com.example.demo.dto.ShipDto;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.model.Image;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.Ship;
import com.example.demo.repository.ShipRepository;
import com.example.demo.service.impl.CottageServiceImpl;
import com.example.demo.service.impl.ShipServiceImpl;
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
@RequestMapping("/ships")
@CrossOrigin(origins = "http://localhost:4200")
public class ShipController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ShipServiceImpl shipService;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ShipRepository shipRepository;



    @GetMapping()
    public List<ShipDto> findAll() {

        return this.shipService.findAll();
    }

    /*
     //ovo samo moze da radi vlasnik broda
    @PostMapping(value = "/createShip")
    public Ship createShip(@RequestBody CreateShipDto newShip) {
        return this.shipService.createShip(newShip);
    }
*/
     //isto radi vlasnik broda
    @GetMapping(value = "/findShip/{id}")
    public ShipDto findShip(@PathVariable Long id) {
        return this.shipService.findShip(id);
    }

    //radi samo vlasnik broda
    @DeleteMapping(value = "/deleteShip/{id}")
    public ResponseEntity<Long> deleteShip(@PathVariable Long id) {
        return this.shipService.deleteShip(id);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    //isto radi vlasnik broda
    @GetMapping(value="/findOwnerShips/{email}")
    public List<ShipDto> findOwnerShips(@PathVariable String email) {
        return this.shipService.getOwnerShips(email);
    }



    @CrossOrigin(origins = "http://localhost:4200")
    //ovo samo moze da radi vlasnik vikendice
    @PutMapping(value = "/editShip")
    public ShipDto editShip(@RequestBody ShipDto newShip) {
        return this.shipService.editShip(newShip);

    }


    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @PostMapping(value = "/add-image/{id}")
    public ResponseEntity<Ship> addImage(@PathVariable Long id,@RequestBody ImageRequest imageRequest) {
        Ship ship = this.shipService.findShipById(id);
        ShipDto shipDto = this.shipService.findShip(id);

        Set<Image> images = new HashSet<>();
        for (Image image:  ship.getImages()) {
            images.add(image);
        }
        images.add(this.imageMapper.mapImageRequestToImage(imageRequest));
        ship.setImages(images);
        return new ResponseEntity<>(this.shipRepository.save(ship)
                , HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/{id}/images")
    public ResponseEntity<ImagesResponse> getShipImages(@PathVariable Long id) {
        Ship ship = this.shipService.findShipById(id);
        Set<Image> images = ship.getImages();
        ImagesResponse response = this.imageMapper.mapImagesToImagesResponse(images);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(value = "/createShips")
    public Ship createCottage(@RequestBody ShipDto newShip) {
        return this.shipService.createShip(newShip);
    }




}
