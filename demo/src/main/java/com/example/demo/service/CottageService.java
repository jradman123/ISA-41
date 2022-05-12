package com.example.demo.service;


import com.example.demo.dto.CottageDto;
import com.example.demo.dto.CreateCottageDto;
import com.example.demo.model.cottages.Cottage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CottageService {
    public List<CottageDto> findAll();
    public CottageDto findCottage(Long id);
    public Cottage createCottage(CreateCottageDto newCottage);
    public List<CottageDto> getOwnerCottages(String email);
    public ResponseEntity<Long> deleteCottage(Long id);

}
