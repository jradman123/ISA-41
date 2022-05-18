package com.example.demo.controller;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.CreateCottageDto;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.impl.CottageServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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




    @GetMapping()
    public List<CottageDto> findAll() {
        return this.cottageService.findAll();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    //ovo samo moze da radi vlasnik vikendice
    @PostMapping(value = "/createCottage")
    public Cottage createCottage(@RequestBody CreateCottageDto newCottage) {
        return this.cottageService.createCottage(newCottage);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    //isto radi vlasnik vikendice
    @GetMapping(value = "/findCottage/{id}")
    public CottageDto findCottage(@PathVariable Long id) {
        return this.cottageService.findCottage(id);
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
    //ovo samo moze da radi vlasnik vikendice
    @PutMapping(value = "/editCottage")
    public CottageDto editCottage(@RequestBody CottageDto newCottage) {
        return this.cottageService.editCottage(newCottage);

    }

}
