package com.example.demo.service.impl;

import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.repository.AdventureUtilityRepository;
import com.example.demo.service.AdventureUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdventureUtilityServiceImpl implements AdventureUtilityService {
    @Autowired
    private AdventureUtilityRepository adventureUtilityRepository;

    @Override
    public AdventureUtility createUtility(AdventureUtility utility) {
        return adventureUtilityRepository.save(utility);
    }

    @Override
    public AdventureUtility deleteById(Long id) {
        AdventureUtility adventureUtility = adventureUtilityRepository.findById(id).get();
        adventureUtility.setDeleted(true);
        return adventureUtilityRepository.save(adventureUtility);
    }

    @Override
    public AdventureUtility updateAdventureUtility(AdventureUtility updatedAdventureUtility,Long id) {
        AdventureUtility adventureUtility = adventureUtilityRepository.findById(id).get();
        adventureUtility.setName(updatedAdventureUtility.getName());
        adventureUtility.setPrice(updatedAdventureUtility.getPrice());
        AdventureUtility saved = adventureUtilityRepository.save(adventureUtility);
        return saved;
    }

    @Override
    public AdventureUtility findById(Long id) {
        return adventureUtilityRepository.findById(id).get();
    }
}
