package com.example.demo.mapper;

import com.example.demo.dto.AdventureRuleDto;
import com.example.demo.dto.FishingEquipmentDto;
import com.example.demo.dto.ResponseFishingEquipment;
import com.example.demo.dto.ResponseRules;
import com.example.demo.model.adventures.AdventureRule;
import com.example.demo.model.adventures.FishingEquipment;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FishingEquipmentMapper {

    public FishingEquipment mapFishingEquipmentDtoToFishingEquipment(FishingEquipmentDto fishingEquipmentDto){
        FishingEquipment fishingEquipment = new FishingEquipment();
        fishingEquipment.setFishingEquipmentName(fishingEquipmentDto.getFishingEquipmentName());
        return fishingEquipment;
    }

    public Set<ResponseFishingEquipment> mapFishingEquipmentsToResponseFishingEquipments(Set<FishingEquipment> equipments){
        Set<ResponseFishingEquipment> responseFishingEquipments = new HashSet<>();
        for (FishingEquipment equipment: equipments) {
            responseFishingEquipments.add(new ResponseFishingEquipment(equipment.getId().toString(),equipment.getFishingEquipmentName()));
        }
        return responseFishingEquipments;
    }
}
