package com.example.demo.controller;

import com.example.demo.dto.CottageDto;
import com.example.demo.service.impl.CottageServiceImpl;
import com.example.demo.service.impl.ShipServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ships")
public class ShipController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ShipServiceImpl shipService;

/*
    @GetMapping()
    public List<ShipDto> findAll() {
        return this.shipService.findAll();
    }

     //ovo samo moze da radi vlasnik broda
    @PostMapping(value = "/createShip")
    public Ship createShip(@RequestBody CreateShipDto newShip) {
        return this.shipService.createShip(newShip);
    }

     //isto radi vlasnik broda
    @GetMapping(value = "/findShip/{id}")
    public CottageDto findCottage(@PathVariable Long id) {
        return this.shipService.findShip(id);
    }

    //radi samo vlasnik broda
    @DeleteMapping(value = "/deleteShip/{id}")
    public ResponseEntity<Long> deleteShip(@PathVariable Long id) {
        return this.shipService.deleteShip(id);
    }

    //isto radi vlasnik vikendice
    @GetMapping(value="/findOwnerShip{email}")
    public List<ShipDto> findShipFromOwner(@PathVariable String email) {
        return this.shipService.getOwnerShips(email);
    }



*/


}
