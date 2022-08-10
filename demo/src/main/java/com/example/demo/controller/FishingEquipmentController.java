package com.example.demo.controller;


import com.example.demo.dto.AdventureRuleDto;
import com.example.demo.dto.FishingEquipmentDto;
import com.example.demo.dto.ResponseFishingEquipment;
import com.example.demo.dto.ResponseRules;
import com.example.demo.mapper.FishingEquipmentMapper;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureRule;
import com.example.demo.model.adventures.FishingEquipment;
import com.example.demo.service.AdventureRuleService;
import com.example.demo.service.AdventureService;
import com.example.demo.service.FishingEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/adventure-fishing-equipments")
public class FishingEquipmentController {

    @Autowired
    private FishingEquipmentService fishingEquipmentService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private FishingEquipmentMapper fishingEquipmentMapper;

    @PreAuthorize("hasAuthority('Instructor')")
    @PostMapping(value = "")
    public ResponseEntity<Set<ResponseFishingEquipment>> addFishingEquipment(@RequestBody FishingEquipmentDto fishingEquipmentDto) {
        FishingEquipment fishingEquipment = this.fishingEquipmentMapper.mapFishingEquipmentDtoToFishingEquipment(fishingEquipmentDto);
        FishingEquipment savedEquipment = this.fishingEquipmentService.addFishingEquipment(fishingEquipment);
        Adventure updated = adventureService.addFishingEquipment(Integer.parseInt(fishingEquipmentDto.getAdventureId()),savedEquipment);
        return new ResponseEntity<>(this.fishingEquipmentMapper.mapFishingEquipmentsToResponseFishingEquipments(updated.getFishingEquipments()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Instructor')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<FishingEquipment> deleteAdventureRule(@PathVariable Long id) {
        return new ResponseEntity<>(fishingEquipmentService.deleteById(id),HttpStatus.OK);
    }
}
