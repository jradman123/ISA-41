package com.example.demo.service.impl;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.RuleDto;

import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.repository.CottageRepository;
import com.example.demo.repository.RuleRepository;
import com.example.demo.service.RuleService;

import org.dom4j.rule.Rule;
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
            if (rule.getCottage() != null) {
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
                rule.setDeleted(true);
                ruleRepository.save(rule);
            }
        }
            return new ResponseEntity<>(id, HttpStatus.OK);
        }


    }
