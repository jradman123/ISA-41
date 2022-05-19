package com.example.demo.service.impl;

import com.example.demo.dto.RuleDto;

import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageUtility;
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
            if (rule.getShip() != null) {
                if (id.equals(rule.getShip().getId())) {
                    ruleDto.add(new RuleDto(rule));
                }
            }
        }
        return ruleDto;

    }

    public Rules createCottageRule(RuleDto newRule) {
        for(Cottage cottage: this.cottageRepository.findAll()){
            if(newRule.getCottageId().equals(cottage.getId())){
                Rules rule = new Rules();
                rule.setCottage(cottage);
                rule.setRuleDescription(newRule.getRuleDescription());
                rule.setDeletedbyCottages(false);
                rule.setDeletedByShip(false);
                rule.setShip(null);

                this.ruleRepository.save(rule);
                return rule;
            }
        }
        return null;
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
}
