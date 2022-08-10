package com.example.demo.service;

import com.example.demo.model.adventures.AdventureUtility;

public interface AdventureUtilityService {

    AdventureUtility createUtility(AdventureUtility utility);
    AdventureUtility deleteById(Long id);
}
