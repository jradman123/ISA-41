package com.example.demo.controller;

import com.example.demo.dto.NavigationDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.model.Rules;
import com.example.demo.model.ships.NavigationalEquipment;
import com.example.demo.service.NavigationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    private NavigationService navigationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/findNavigationbyBoat/{id}")
    public List<NavigationDto> findNavigationbyBoat(@PathVariable Long id) {


        return navigationService.findNavigationbyBoat(id);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    //ovo samo moze da radi vlasnik vikendice
    @PostMapping(value = "/createNavigation")
    public NavigationalEquipment createShipNavigation(@RequestBody NavigationDto newNavigation) {
        return this.navigationService.createNavigation(newNavigation);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    //radi samo vlasnik vikendice
    @DeleteMapping(value = "/deleteNavigationByBoat/{id}/{idShip}")
    public ResponseEntity<Long> deleteNavigationByShip(@PathVariable Long id, @PathVariable Long idShip) {
        return this.navigationService.deleteNavigationByShip(id,idShip);
    }


}
