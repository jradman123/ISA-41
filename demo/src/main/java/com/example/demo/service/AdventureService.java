package com.example.demo.service;

import com.example.demo.model.adventures.Adventure;

import java.util.List;

public interface AdventureService {

    List<Adventure> getAllForInstructor(int instructorId);
    void createAdventure(Adventure newAdventure);
}
