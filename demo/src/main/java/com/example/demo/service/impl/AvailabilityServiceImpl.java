package com.example.demo.service.impl;

import com.example.demo.dto.CottageAvailabilityDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.repository.CottageAvailabilityRepository;
import com.example.demo.repository.CottageRepository;
import com.example.demo.repository.RuleRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private CottageAvailabilityRepository cottageAvailabilityRepository;


    @Autowired
    private CottageRepository cottageRepository;


    @Autowired
    private CottageServiceImpl cottageService;

    @Override
    public List<CottageAvailabilityDto> findByCottage(Long id)
    {
        List<CottageAvailabilityDto> cottageAvailabilityDtos = new ArrayList<>();
        for (CottageAvailability ca : cottageAvailabilityRepository.findAll()) {

            if (ca.getCottage() != null) {
                if (id.equals(ca.getCottage().getId())) {
                    cottageAvailabilityDtos.add(new CottageAvailabilityDto(ca));

                }
            }
        }
        return cottageAvailabilityDtos;
    }





    @Override
    public CottageAvailability add(CottageAvailabilityDto cottageAvailabilityDto) {
        System.out.print("dsdsds"+cottageAvailabilityDto);
        Cottage cottage=cottageService.findCottageById(cottageAvailabilityDto.getCottageId());
        CottageAvailability ca=new CottageAvailability(cottageAvailabilityDto.getStartDate(),cottageAvailabilityDto.getEndDate(),cottage);
        return  this.cottageAvailabilityRepository.save(ca);
    }
}
