package com.example.demo.service.impl;

import com.example.demo.dto.CottageAvailabilityDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.dto.ShipAvailabilityDto;
import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipAvailability;
import com.example.demo.model.users.InstructorAvailability;
import com.example.demo.repository.*;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private CottageAvailabilityRepository cottageAvailabilityRepository;

    @Autowired
    private ShipAvailabilityRepository shipAvailabilityRepository;

    @Autowired
    private ShipRepository shipRepository;


    @Autowired
    private CottageRepository cottageRepository;


    @Autowired
    private CottageServiceImpl cottageService;

    @Autowired
    private ShipServiceImpl shipService;

    @Override
    public List<CottageAvailabilityDto> findByCottage(Long id) {
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
        System.out.print("dsdsds" + cottageAvailabilityDto);
        Cottage cottage = cottageService.findCottageById(cottageAvailabilityDto.getCottageId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        CottageAvailability ca = new CottageAvailability(LocalDateTime.parse(cottageAvailabilityDto.getStartDate(),formatter),LocalDateTime.parse(cottageAvailabilityDto.getEndDate(),formatter), cottage);
       // CottageAvailability cottageAvailability = cottageAvailabilityRepository.save(ca);
        List<CottageAvailability> availabilities = this.cottageAvailabilityRepository.getAllForCottage(ca.getCottage().getId());
        List<CottageAvailability> newAvailabilities = checkForOverlappingAvailabilities(availabilities, ca);
        for(CottageAvailability caa:newAvailabilities) {
            cottageAvailabilityRepository.save(caa);
        }

        return ca;


    }

    @Override
    public List<ShipAvailabilityDto> findByShip(Long id) {
        List<ShipAvailabilityDto> shipAvailabilityDtos = new ArrayList<>();
        for (ShipAvailability ca : shipAvailabilityRepository.findAll()) {

            if (ca.getShip() != null) {
                if (id.equals(ca.getShip().getId())) {
                    shipAvailabilityDtos.add(new ShipAvailabilityDto(ca));

                }
            }
        }
        return shipAvailabilityDtos;
    }

    @Override
    public ShipAvailability addShipAvailability(ShipAvailabilityDto shipAvailabilityDto) {
        System.out.print("dsdsds" + shipAvailabilityDto);
        Ship ship = shipService.findShipById(shipAvailabilityDto.getShipId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        ShipAvailability ca = new ShipAvailability(LocalDateTime.parse(shipAvailabilityDto.getStartDate(),formatter),LocalDateTime.parse( shipAvailabilityDto.getEndDate(),formatter), ship);
   //     ShipAvailability shipAvailability = shipAvailabilityRepository.save(ca);
        List<ShipAvailability> availabilities = this.shipAvailabilityRepository.getAllForShip(ca.getShip().getId());
        List<ShipAvailability> newAvailabilities = checkForOverlappingShipAvailabilities(availabilities, ca);
        for(ShipAvailability saa:newAvailabilities) {
            shipAvailabilityRepository.save(saa);
        }

        return ca;
    }

    private List<ShipAvailability> checkForOverlappingShipAvailabilities(List<ShipAvailability> availabilities, ShipAvailability shipAvailability) {

        List<ShipAvailability> newAvailabilities = new ArrayList<>();
        for (ShipAvailability availability : availabilities) {
            if (!availability.getId().equals(shipAvailability.getId()) &&
                    (isBetweenShipAvailabilityDate(shipAvailability.getStartDate(), availability)
                            || isBetweenShipAvailabilityDate(shipAvailability.getEndDate(), availability))) {
                LocalDateTime startDate = availability.getStartDate();
                LocalDateTime endDate = availability.getEndDate();
                shipAvailabilityRepository.delete(availability);
                availability = calculateNewShipAvailability(startDate, endDate, shipAvailability);
            } else if (shipAvailability.getStartDate().isBefore(availability.getStartDate())
                    && shipAvailability.getEndDate().isAfter(availability.getEndDate())) {
                availability.setStartDate(shipAvailability.getStartDate());
                availability.setEndDate(shipAvailability.getEndDate());
            }
            newAvailabilities.add(availability);
        }

        return newAvailabilities;
    }

    private ShipAvailability calculateNewShipAvailability(LocalDateTime startDate, LocalDateTime endDate, ShipAvailability shipAvailability) {
        if (startDate.isBefore(shipAvailability.getStartDate()))
            shipAvailability.setStartDate(startDate);

        if (endDate.isAfter(shipAvailability.getEndDate()))
            shipAvailability.setEndDate(endDate);

        return shipAvailability;
    }

    private boolean isBetweenShipAvailabilityDate(LocalDateTime startDate, ShipAvailability availability) {
        return (startDate.isBefore(availability.getEndDate()) && startDate.isAfter(availability.getStartDate()))
                || startDate.equals(availability.getEndDate()) || startDate.equals(availability.getStartDate());
    }





    private List<CottageAvailability> checkForOverlappingAvailabilities(List<CottageAvailability> availabilityPeriods, CottageAvailability newAvailability) {
        List<CottageAvailability> newAvailabilities = new ArrayList<>();
        for (CottageAvailability availability : availabilityPeriods) {
            if (!availability.getId().equals(newAvailability.getId()) &&
                    (isBetweenAvailabilityDate(newAvailability.getStartDate(), availability)
                            || isBetweenAvailabilityDate(newAvailability.getEndDate(), availability))) {
                LocalDateTime startDate = availability.getStartDate();
                LocalDateTime endDate = availability.getEndDate();
                cottageAvailabilityRepository.delete(availability);
                availability = calculateNewAvailability(startDate, endDate, newAvailability);
            } else if (newAvailability.getStartDate().isBefore(availability.getStartDate())
                    && newAvailability.getEndDate().isAfter(availability.getEndDate())) {
                availability.setStartDate(newAvailability.getStartDate());
                availability.setEndDate(newAvailability.getEndDate());
            }
            newAvailabilities.add(availability);
        }

        return newAvailabilities;
    }
    private boolean isBetweenAvailabilityDate(LocalDateTime newDate, CottageAvailability availability) {
        return (newDate.isBefore(availability.getEndDate()) && newDate.isAfter(availability.getStartDate()))
                || newDate.equals(availability.getEndDate()) || newDate.equals(availability.getStartDate());
    }

    private CottageAvailability calculateNewAvailability(LocalDateTime startDate, LocalDateTime endDate, CottageAvailability newAvailability) {
        if (startDate.isBefore(newAvailability.getStartDate()))
            newAvailability.setStartDate(startDate);

        if (endDate.isAfter(newAvailability.getEndDate()))
            newAvailability.setEndDate(endDate);

        return newAvailability;
    }



}