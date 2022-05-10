package com.example.demo.service;


import com.example.demo.dto.CottageDto;
import com.example.demo.model.cottages.Cottage;

import java.util.List;

public interface CottageService {
    public List<CottageDto> findAll();
    public Cottage findCottage(Long id);
}
