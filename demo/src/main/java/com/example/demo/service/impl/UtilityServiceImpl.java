package com.example.demo.service.impl;

import com.example.demo.dto.CottageUtilityDto;
import com.example.demo.dto.ShipUtilityDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.model.Utility;
import com.example.demo.model.adventures.AdventureUtility;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipUtility;
import com.example.demo.repository.CottageUtilityRepository;
import com.example.demo.repository.ShipUtilityRepository;
import com.example.demo.repository.UtilityRepository;
import com.example.demo.service.UtiilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilityServiceImpl implements UtiilityService {
    @Autowired
    private UtilityRepository utilityRepository;
    @Autowired
    private CottageUtilityRepository cottageUtilityRepositoty;

    @Autowired
    private ShipUtilityRepository shipUtilityRepository;

    @Autowired
    private CottageServiceImpl cottageService;

    @Autowired
    private ShipServiceImpl shipService;



    @Override
    public List<CottageUtilityDto> getUtilityByCottage(Long id) {
        List<CottageUtilityDto> utilityDto=new ArrayList<>();
        for(CottageUtility cottageUtility: cottageUtilityRepositoty.findAll()) {
            if(id.equals(cottageUtility.getCottage().getId()) & cottageUtility.isDeleted()==false) {
               for(Utility utility:utilityRepository.findAll()) {
                    if(cottageUtility.getUtility().getId().equals(utility.getId())) {
                        utilityDto.add(new CottageUtilityDto(cottageUtility));
                    }
                }

            }
        }

       return utilityDto;
    }

    @Override
    public List<ShipUtilityDto> getUtilitiesbyBoat(Long id) {
        List<ShipUtilityDto> utilityDto=new ArrayList<>();
        for(ShipUtility shipUtility: shipUtilityRepository.findAll()) {
            if(id.equals(shipUtility.getShip().getId()) & shipUtility.isDeleted()==false) {
                for(Utility utility:utilityRepository.findAll()) {
                    if(shipUtility.getUtility().getId().equals(utility.getId())) {
                        utilityDto.add(new ShipUtilityDto(shipUtility));
                    }
                }

            }
        }

        return utilityDto;
    }

    @Override
    public ResponseEntity<Long> deleteUtiliry(Long id, Long idCottage) {
        List<CottageUtility> cottageUtilities=this.cottageUtilityRepositoty.findAll();
        for (CottageUtility utility: cottageUtilities)
        {
            if(utility.getCottage().getId()==idCottage & utility.getId()==id) {
                utility.setDeleted(true);
                cottageUtilityRepositoty.save(utility);

            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @Override
    public CottageUtility add(CottageUtilityDto cottageUtilityDto) {
        Utility utility=new Utility(cottageUtilityDto.getName());
        Cottage cottage=cottageService.findCottageById(cottageUtilityDto.getCottageId());
        CottageUtility ca=new CottageUtility(cottageUtilityDto.getPrice(),cottage,utility);
        this.utilityRepository.save(utility);
        return  this.cottageUtilityRepositoty.save(ca);

    }

    @Override
    public CottageUtility updateCottageUtility(CottageUtilityDto cottageUtilityDto,Long id) {
        CottageUtility cottageUtility = cottageUtilityRepositoty.findById(id).get();

       return cottageUtility;
    }

    @Override
    public ShipUtility addUtilityByShip(ShipUtilityDto shipUtilityDto) {
        Utility utility=new Utility(shipUtilityDto.getName());
        Ship ship=shipService.findShipById(shipUtilityDto.getShipId());
        ShipUtility ca=new ShipUtility(shipUtilityDto.getPrice(),ship,utility);
        this.utilityRepository.save(utility);
        return  this.shipUtilityRepository.save(ca);
    }

    @Override
    public ResponseEntity<Long> deleteUtilirybyShip(Long id, Long idShip) {
        List<ShipUtility> shipUtilities=this.shipUtilityRepository.findAll();
        for (ShipUtility utility: shipUtilities)
        {
            if(utility.getShip().getId()==idShip & utility.getId()==id) {
                utility.setDeleted(true);
                shipUtilityRepository.save(utility);

            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

}
