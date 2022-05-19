package com.example.demo.service;

import com.example.demo.dto.UtilityDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UtiilityService {


    List<UtilityDto> getUtilityByCottage(Long id);

    List<UtilityDto> getUtilitiesbyBoat(Long id);

    ResponseEntity<Long> deleteUtiliry(Long id, Long idCottage);
}
