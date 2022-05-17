package com.example.demo.service.impl;

import com.example.demo.dto.UtilityDto;
import com.example.demo.model.Utility;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.ships.ShipUtility;
import com.example.demo.repository.CottageUtilityRepository;
import com.example.demo.repository.ShipUtilityRepository;
import com.example.demo.repository.UtilityRepository;
import com.example.demo.service.UtiilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilityServiceImpl implements UtiilityService {
    @Autowired
    private UtilityRepository utilityRepository;
    @Autowired
    private CottageUtilityRepository cottageUtilityRepositoty;

    @Autowired
    private ShipUtilityRepository shipUtilityRepository;



    @Override
    public List<UtilityDto> getUtilityByCottage(Long id) {
        List<UtilityDto> utilityDto=new ArrayList<>();
        for(CottageUtility cottageUtility: cottageUtilityRepositoty.findAll()) {
            if(id.equals(cottageUtility.getCottage().getId())) {
               for(Utility utility:utilityRepository.findAll()) {
                    if(cottageUtility.getUtility().getId().equals(utility.getId())) {
                        utilityDto.add(new UtilityDto(utility));
                    }
                }

            }
        }

       return utilityDto;
    }

    @Override
    public List<UtilityDto> getUtilitiesbyBoat(Long id) {
        List<UtilityDto> utilityDto=new ArrayList<>();
        for(ShipUtility shipUtility: shipUtilityRepository.findAll()) {
            if(id.equals(shipUtility.getShip().getId())) {
                for(Utility utility:utilityRepository.findAll()) {
                    if(shipUtility.getUtility().getId().equals(utility.getId())) {
                        utilityDto.add(new UtilityDto(utility));
                    }
                }

            }
        }

        return utilityDto;
    }
}
