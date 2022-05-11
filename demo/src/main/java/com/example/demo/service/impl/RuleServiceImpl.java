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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private CottageRepository cottageRepository;



/*
    @Override
    public List<RuleDto> getRulesByCottage(Long id)
    {
        List<RuleDto> ruleDto = new ArrayList<>();

        Cottage cottage = cottageRepository.findById(id).orElse(null);
        for(Rules rule:cottage.getRules()) {
            ruleDto.add(new RuleDto(rule));
        }
        return ruleDto;


        }


 */
}
