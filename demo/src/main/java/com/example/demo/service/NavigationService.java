package com.example.demo.service;


import com.example.demo.dto.NavigationDto;
import com.example.demo.dto.UtilityDto;

import java.util.List;

public interface NavigationService {
    List<NavigationDto> findNavigationbyBoat(Long id);
}
