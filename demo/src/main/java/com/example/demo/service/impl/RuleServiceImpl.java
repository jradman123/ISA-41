package com.example.demo.service.impl;

import com.example.demo.dto.RuleDto;

import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.ships.Ship;
import com.example.demo.repository.CottageRepository;
import com.example.demo.repository.RuleRepository;
import com.example.demo.service.RuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private CottageServiceImpl cottageService;

    @Autowired
    private ShipServiceImpl shipService;


    @Override
    public List<RuleDto> getRulesByCottage(Long id) {
        List<RuleDto> ruleDto = new ArrayList<>();
        for (Rules rule : ruleRepository.findAll()) {

            if (rule.getCottage() != null & rule.isDeletedbyCottages()==false) {
                if (id.equals(rule.getCottage().getId())) {
                    ruleDto.add(new RuleDto(rule));

                }
            }
        }
        return ruleDto;


    }


    public RuleDto findRule(Long id) {
        Rules rule = ruleRepository.findById(id).orElse(null);
        RuleDto ruleDto = new RuleDto(rule);
        return ruleDto;
    }

    public ResponseEntity<Long> deleteRule(Long id) {
        for (Rules rule : ruleRepository.findAll()) {
            if (id == rule.getId()) {
                rule.setDeletedbyCottages(true);
                ruleRepository.save(rule);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    public List<RuleDto> getRulesByBoat(Long id) {
        List<RuleDto> ruleDto = new ArrayList<>();
        for (Rules rule : ruleRepository.findAll()) {

            if (rule.getShip() != null & rule.isDeletedByShip()==false) {
                if (id.equals(rule.getShip().getId())) {
                    ruleDto.add(new RuleDto(rule));

                }
            }
        }
        return ruleDto;

    }

    public Rules createCottageRule(RuleDto newRule) {


        System.out.print("dsdsds"+newRule);
        Cottage cottage=cottageService.findCottageById(newRule.getCottageId());
        Rules rule=new Rules();

        rule.setCottage(cottage);
        rule.setRuleDescription(newRule.getRuleDescription());
        rule.setDeletedbyCottages(false);
        rule.setDeletedByShip(false);
        rule.setShip(null);

        this.ruleRepository.save(rule);
        return rule;


    }

     @Override
    public ResponseEntity<Long> deleteRuleByCottage(Long id, Long idCottage) {

         List<Rules> rules=this.ruleRepository.findAll();
         for (Rules rule: rules)
         {
             if(rule.getCottage().getId()==idCottage & rule.getId()==id) {
                 rule.setDeletedbyCottages(true);
                 ruleRepository.save(rule);
             }

         }
         return new ResponseEntity<>(id, HttpStatus.OK);

     }

    @Override
    public ResponseEntity<Long> deleteRuleByShip(Long id, Long idShip) {
        List<Rules> rules=this.ruleRepository.findAll();
        for (Rules rule: rules)
        {
            if(rule.getShip().getId()==idShip & rule.getId()==id) {
                rule.setDeletedByShip(true);
                ruleRepository.save(rule);
            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    public Rules createShipRule(RuleDto newRule) {

        System.out.print("dsdsds"+newRule);
        Ship ship=shipService.findShipById(newRule.getShipId());
        Rules rule=new Rules();

        rule.setShip(ship);
        rule.setRuleDescription(newRule.getRuleDescription());
        rule.setDeletedbyCottages(false);
        rule.setDeletedByShip(false);
        rule.setCottage(null);
        this.ruleRepository.save(rule);
        return rule;
    }

}
