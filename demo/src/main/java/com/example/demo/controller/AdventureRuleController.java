package com.example.demo.controller;

import com.example.demo.dto.AdventureRuleDto;
import com.example.demo.dto.ResponseRules;
import com.example.demo.mapper.AdventureRuleMapper;
import com.example.demo.model.Rules;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureRule;
import com.example.demo.service.AdventureRuleService;
import com.example.demo.service.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/adventure-rules")
public class AdventureRuleController {

    @Autowired
    private AdventureRuleService adventureRuleService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private AdventureRuleMapper adventureRuleMapper;

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping(value = "")
    public ResponseEntity<Set<ResponseRules>> addAdventureRule(@RequestBody AdventureRuleDto newAdventureRule) {
        AdventureRule rules = this.adventureRuleMapper.mapAdventureRuleDtoToRule(newAdventureRule);
        AdventureRule savedRule = this.adventureRuleService.addAdventureRule(rules);
        Adventure updated = adventureService.addRule(Integer.parseInt(newAdventureRule.getAdventureId()),savedRule);
        return new ResponseEntity<>(this.adventureRuleMapper.mapRulesToResponseRules(updated.getRules()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<AdventureRule> deleteAdventureRule(@PathVariable Long id) {
        return new ResponseEntity<>(adventureRuleService.deleteById(id),HttpStatus.OK);
    }
}
