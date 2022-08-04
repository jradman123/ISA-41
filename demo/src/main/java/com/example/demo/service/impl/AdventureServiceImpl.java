package com.example.demo.service.impl;

import com.example.demo.controller.AdventureController;
import com.example.demo.model.adventures.Adventure;
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
    public void createAdventure(Adventure newAdventure) {
        adventureRepository.save(newAdventure);
    }
}
