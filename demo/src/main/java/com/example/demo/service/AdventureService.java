package com.example.demo.service;

import com.example.demo.dto.AdventureAverageRating;
import com.example.demo.dto.AverageMarkDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.model.Rules;
import com.example.demo.model.Utility;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureRule;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.model.adventures.FishingEquipment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AdventureService {

    List<Adventure> getAllForInstructor(int instructorId);
    Adventure createAdventure(Adventure newAdventure);
    Adventure findAdventure(int id);
    Adventure updateAdventure(Adventure updatedAdventure,int id);
    Adventure addRule(int id, AdventureRule rule);
    Set<AdventureRule> getRulesByAdventure(Adventure adventure);
    Adventure addFishingEquipment(int id, FishingEquipment fishingEquipment);
    Set<FishingEquipment> getFishingEquipmentByAdventure(Adventure adventure);
    Adventure addUtility(int id, AdventureUtility utility);
    Set<AdventureUtility> getUtilitiesByAdventure(Adventure adventure);
    Adventure deleteAdventure(int id);
    AdventureAverageRating getRatingForAdventure(int id);
    List<Adventure> getAllUndeleted();

}
