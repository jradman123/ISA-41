package com.example.demo.service.impl;

import com.example.demo.dto.NavigationDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.NavigationalEquipment;
import com.example.demo.model.ships.Ship;
import com.example.demo.repository.NavigationRepository;
import com.example.demo.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavigationServiceImpl implements NavigationService
{
    @Autowired
    private NavigationRepository navigationRepository;
    @Autowired
    private ShipServiceImpl shipService;


    @Override
    public List<NavigationDto> findNavigationbyBoat(Long id) {
        List<NavigationDto> navigationDto = new ArrayList<>();
        for (NavigationalEquipment navigation : navigationRepository.findAll()) {
            if (navigation.getShip() != null && navigation.isDeleted()==false) {
                if (id.equals(navigation.getShip().getId())) {
                    navigationDto.add(new NavigationDto(navigation));
                }
            }
        }
        return navigationDto;
    }

    @Override
    public NavigationalEquipment createNavigation(NavigationDto newNavigation) {
        Ship ship=shipService.findShipById(Long.parseLong(newNavigation.getShipId()));
        NavigationalEquipment navigationalEquipment=new NavigationalEquipment();

        navigationalEquipment.setShip(ship);
        navigationalEquipment.setName(newNavigation.getName());
        navigationalEquipment.setDeleted(false);


        this.navigationRepository.save(navigationalEquipment);
        return navigationalEquipment;

    }

    @Override
    public ResponseEntity<Long> deleteNavigationByShip(Long id, Long idShip) {
        List<NavigationalEquipment> navigations=this.navigationRepository.findAll();
        for (NavigationalEquipment navigation: navigations)
        {
            if(navigation.getShip().getId()==idShip & navigation.getId()==id) {
                navigation.setDeleted(true);
                navigationRepository.save(navigation);
            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
