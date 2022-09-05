package com.example.demo.controller;

import com.example.demo.dto.RuleDto;
import com.example.demo.model.Rules;
import com.example.demo.service.impl.RuleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleServiceImpl ruleService;


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @DeleteMapping(value = "/deleteRyleByCottage/{id}/{idCottage}")
    public ResponseEntity<Long> deleteRuleByCottage(@PathVariable Long id,@PathVariable Long idCottage) {
        return this.ruleService.deleteRuleByCottage(id,idCottage);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @DeleteMapping(value = "/deleteRyleByShip/{id}/{idShip}")
    public ResponseEntity<Long> deleteRuleByShip(@PathVariable Long id,@PathVariable Long idShip) {
        return this.ruleService.deleteRuleByShip(id,idShip);
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('Admin')")
    @GetMapping(value="/findRulesByCottage/{id}")
    public List<RuleDto> getRulesByCottage(@PathVariable Long id) {
        return ruleService.getRulesByCottage(id);
    }


    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('ShipAdvertiser')")
    @GetMapping(value = "/findRule/{id}")
    public RuleDto findRule(@PathVariable Long id) {
        return this.ruleService.findRule(id);
    }


    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('ShipAdvertiser')")
    public ResponseEntity<Long> deleteRule(@PathVariable Long id) {
        return this.ruleService.deleteRule(id);
    }

    @GetMapping(value="/findRulesByBoat/{id}")
    @PreAuthorize("hasAuthority('ShipAdvertiser') || hasAuthority('Admin')")
    public List<RuleDto> getRulesByBoat(@PathVariable Long id)
    {
        return ruleService.getRulesByBoat(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @PostMapping(value = "/createCottageRule")
    public Rules createCottageRule(@RequestBody RuleDto newRule) {
        return this.ruleService.createCottageRule(newRule);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @PostMapping(value = "/createShipRule")
    public Rules createShipRule(@RequestBody RuleDto newRule) {
        return this.ruleService.createShipRule(newRule);
    }


}









