package com.example.demo.service.impl;

import com.example.demo.model.Rules;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureRule;
import com.example.demo.repository.AdventureRepository;
import com.example.demo.service.AdventureRuleService;
import com.example.demo.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdventureServiceImpl implements AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    @Override
    public List<Adventure> getAllForInstructor(int instructorId) {
        return adventureRepository.getAllForInstructor(instructorId);
    }

    @Override
    public Adventure createAdventure(Adventure newAdventure) {
        return adventureRepository.save(newAdventure);
    }

    @Override
    public Adventure findAdventure(int id) {
        return adventureRepository.findById(id).get();
    }

    @Override
    public Adventure updateAdventure(Adventure updatedAdventure,int id) {
        Adventure adventure = adventureRepository.findById(id).get();
        adventure.setName(updatedAdventure.getName());
        adventure.setDescription(updatedAdventure.getDescription());
        adventure.setPrice(updatedAdventure.getPrice());
        adventure.getAddress().setStreetNumber(updatedAdventure.getAddress().getStreetNumber());
        adventure.getAddress().setStreetName(updatedAdventure.getAddress().getStreetName());
        adventure.getAddress().setCity(updatedAdventure.getAddress().getCity());
        adventure.getAddress().setCountry(updatedAdventure.getAddress().getCountry());
        adventure.setGuestLimit(updatedAdventure.getGuestLimit());
        adventure.setCancellationConditions(updatedAdventure.getCancellationConditions());
        Adventure saved = adventureRepository.save(adventure);
        return saved;
    }

    @Override
    public Adventure addRule(int id, AdventureRule rule) {
        Adventure adventure = findAdventure(id);
        Set<AdventureRule> adventureRules = new HashSet<>();
        for (AdventureRule rules: adventure.getRules()) {
            adventureRules.add(rules);
        }
        adventureRules.add(rule);
        adventure.setRules(adventureRules);
        adventureRepository.save(adventure);
        return adventure;
    }

    @Override
    public Set<AdventureRule> getRulesByAdventure(Adventure adventure) {
        Adventure adventureDb = findAdventure(adventure.getId());
        Set<AdventureRule> adventureRules = new HashSet<>();
        for (AdventureRule rules: adventureDb.getRules()) {
            if(!rules.isDeleted()) {
                adventureRules.add(rules);
            }
        }
        return adventureRules;
    }
}
