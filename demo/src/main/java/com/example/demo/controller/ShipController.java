package com.example.demo.controller;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.ShipDto;
import com.example.demo.model.ships.Ship;
import com.example.demo.service.impl.CottageServiceImpl;
import com.example.demo.service.impl.ShipServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ships")
public class ShipController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ShipServiceImpl shipService;


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
    public ShipDto findCottage(@PathVariable Long id) {
        return this.shipService.findShip(id);
    }

    //radi samo vlasnik broda
    @DeleteMapping(value = "/deleteShip/{id}")
    public ResponseEntity<Long> deleteShip(@PathVariable Long id) {
        return this.shipService.deleteShip(id);
    }


    //isto radi vlasnik broda
    @GetMapping(value="/findOwnerShips/{email}")
    public List<ShipDto> findOwnerShips(@PathVariable String email) {
        return this.shipService.getOwnerShips(email);
    }






}
