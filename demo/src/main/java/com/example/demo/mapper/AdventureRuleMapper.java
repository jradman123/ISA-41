package com.example.demo.mapper;

import com.example.demo.dto.AdventureRuleDto;
import com.example.demo.dto.ResponseRules;
import com.example.demo.model.Rules;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureRule;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AdventureRuleMapper {

    public AdventureRule mapAdventureRuleDtoToRule(AdventureRuleDto adventureRuleDto){
        AdventureRule rules = new AdventureRule();
        rules.setRuleDescription(adventureRuleDto.getRuleDescription());
        return rules;
    }

    public Set<ResponseRules> mapRulesToResponseRules(Set<AdventureRule> rules){
        Set<ResponseRules> responseRules = new HashSet<>();
        for (AdventureRule rule: rules) {
            responseRules.add(new ResponseRules(rule.getId().toString(),rule.getRuleDescription()));
        }
        return responseRules;
    }
}
