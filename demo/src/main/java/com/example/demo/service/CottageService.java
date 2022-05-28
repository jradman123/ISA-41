package com.example.demo.service;


import com.example.demo.dto.CottageDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.model.cottages.Cottage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CottageService {
    public List<CottageDto> findAll();
    public CottageDto findCottage(Long id);
    public Cottage createCottage(CottageDto newCottage);
    public List<CottageDto> getOwnerCottages(String email);
    public ResponseEntity<Long> deleteCottage(Long id);

    CottageDto editCottage(CottageDto cottageDto);


    public List<RoomDto> findRoomsByCottage(Long id);
}
