package com.example.demo.service;


import com.example.demo.model.adventures.FishingEquipment;

public interface FishingEquipmentService {

    FishingEquipment addFishingEquipment(FishingEquipment rule);
    FishingEquipment deleteById(Long id);
}
