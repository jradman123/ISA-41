package com.example.demo.service;

import com.example.demo.dto.CottageAvailabilityDto;
import com.example.demo.model.cottages.CottageAvailability;

import java.util.List;

public interface AvailabilityService {
    List<CottageAvailabilityDto> findByCottage(Long id);

    CottageAvailability add(CottageAvailabilityDto cottageAvailabilityDto);
}
