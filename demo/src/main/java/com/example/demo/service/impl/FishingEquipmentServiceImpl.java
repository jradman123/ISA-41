package com.example.demo.service.impl;

import com.example.demo.model.adventures.AdventureRule;
import com.example.demo.model.adventures.FishingEquipment;
import com.example.demo.repository.FishingEquipmentRepository;
import com.example.demo.service.FishingEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishingEquipmentServiceImpl implements FishingEquipmentService {

    @Autowired
    private FishingEquipmentRepository fishingEquipmentRepository;

    @Override
    public FishingEquipment addFishingEquipment(FishingEquipment rule) {
        return fishingEquipmentRepository.save(rule);
    }

    @Override
    public FishingEquipment deleteById(Long id) {
        FishingEquipment fishingEquipment = fishingEquipmentRepository.findById(id).get();
        fishingEquipment.setDeleted(true);
        return fishingEquipmentRepository.save(fishingEquipment);
    }
}
