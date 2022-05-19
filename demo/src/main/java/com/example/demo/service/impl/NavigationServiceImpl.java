package com.example.demo.service.impl;

import com.example.demo.dto.NavigationDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.model.Rules;
import com.example.demo.model.ships.NavigationalEquipment;
import com.example.demo.repository.NavigationRepository;
import com.example.demo.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavigationServiceImpl implements NavigationService
{
    @Autowired
    private NavigationRepository navigationRepository;


    @Override
    public List<NavigationDto> findNavigationbyBoat(Long id) {
        List<NavigationDto> navigationDto = new ArrayList<>();
        for (NavigationalEquipment navigation : navigationRepository.findAll()) {
            if (navigation.getShip() != null) {
                if (id.equals(navigation.getShip().getId())) {
                    navigationDto.add(new NavigationDto(navigation));
                }
            }
        }
        return navigationDto;
    }
}
