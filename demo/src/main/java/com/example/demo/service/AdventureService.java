package com.example.demo.service;

import com.example.demo.dto.CottageDto;
import com.example.demo.model.adventures.Adventure;

import java.util.List;
import java.util.Optional;

public interface AdventureService {

    List<Adventure> getAllForInstructor(int instructorId);
    Adventure createAdventure(Adventure newAdventure);
    Adventure findAdventure(int id);
    Adventure updateAdventure(Adventure updatedAdventure,int id);

}
