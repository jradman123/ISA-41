package com.example.demo.service.impl;

import com.example.demo.model.adventures.AdventureRule;
import com.example.demo.repository.AdventureRuleRepository;
import com.example.demo.service.AdventureRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdventureRuleServiceImpl implements AdventureRuleService {

    @Autowired
    private AdventureRuleRepository adventureRuleRepository;

    @Override
    public AdventureRule addAdventureRule(AdventureRule rule) {
        return adventureRuleRepository.save(rule);
    }

    @Override
    public AdventureRule deleteById(Long id) {
        AdventureRule adventureRule = adventureRuleRepository.findById(id).get();
        adventureRule.setDeleted(true);
        return adventureRuleRepository.save(adventureRule);
    }
}
