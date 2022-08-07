package com.example.demo.service;

import com.example.demo.model.Rules;
import com.example.demo.model.adventures.AdventureRule;
import org.springframework.stereotype.Service;

public interface AdventureRuleService {
    AdventureRule addAdventureRule(AdventureRule rule);
    AdventureRule deleteById(Long id);
}
