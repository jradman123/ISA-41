package com.example.demo.controller;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.CreateCottageDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.service.impl.RuleServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleServiceImpl ruleService;

   @GetMapping(value="/findRulesByCottage/{id}")
    public List<RuleDto> getRulesByCottage(@PathVariable Long id) {
        return ruleService.getRulesByCottage(id);
    }



    @GetMapping(value = "/findRule/{id}")
    public RuleDto findCottage(@PathVariable Long id) {
        return this.ruleService.findRule(id);
    }



    @DeleteMapping(value = "/deleteRule/{id}")
    public ResponseEntity<Long> deleteCottage(@PathVariable Long id) {
        return this.ruleService.deleteRule(id);
    }

    @GetMapping(value="/findRulesByBoat/{id}")
    public List<RuleDto> getRulesByBoat(@PathVariable Long id) {
        return ruleService.getRulesByBoat(id);
    }

    //ovo samo moze da radi vlasnik vikendice
    @PostMapping(value = "/createCottageRule")
    public Rules createCottageRule(@RequestBody RuleDto newRule) {
        return this.ruleService.createCottageRule(newRule);
    }
}









