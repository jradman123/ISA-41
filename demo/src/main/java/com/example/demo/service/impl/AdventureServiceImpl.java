package com.example.demo.service.impl;

import com.example.demo.dto.CottageDto;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.repository.AdventureRepository;
import com.example.demo.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
