package com.example.demo.service;

import com.example.demo.dto.UtilityDto;

import java.util.List;

public interface UtiilityService {


    List<UtilityDto> getUtilityByCottage(Long id);

    List<UtilityDto> getUtilitiesbyBoat(Long id);
}
