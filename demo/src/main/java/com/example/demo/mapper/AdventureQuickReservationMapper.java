package com.example.demo.mapper;

import com.example.demo.dto.AdventureQuickReservationDto;
import com.example.demo.dto.AdventureQuickReservationResponse;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureQuickReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AdventureQuickReservationMapper {

    @Autowired
    private AdventureUtilityMapper adventureUtilityMapper;

    public AdventureQuickReservation mapToAdventureQuickReservation(AdventureQuickReservationDto adventureQuickReservationDto,
                                                                    Adventure adventure){
        AdventureQuickReservation adventureQuickReservation = new AdventureQuickReservation();
        adventureQuickReservation.setAdventure(adventure);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        adventureQuickReservation.setStartTime(LocalDateTime.parse(adventureQuickReservationDto.getStartTime(),formatter));
        adventureQuickReservation.setEndTime(LocalDateTime.parse(adventureQuickReservationDto.getEndTime(),formatter));
        adventureQuickReservation.setGuestLimit(Integer.parseInt(adventureQuickReservationDto.getGuestLimit()));
        adventureQuickReservation.setPrice(Double.parseDouble(adventureQuickReservationDto.getPrice()));
        adventureQuickReservation.setValidUntil(LocalDateTime.parse(adventureQuickReservationDto.getValidUntil(),formatter));
        return adventureQuickReservation;
    }

    public AdventureQuickReservationResponse mapToAdventureQuickReservationResponse(AdventureQuickReservation adventureQuickReservation){
        AdventureQuickReservationResponse adventureQuickReservationResponse = new AdventureQuickReservationResponse();
        adventureQuickReservationResponse.setAdventureId(adventureQuickReservation.getAdventure().getId().toString());
        adventureQuickReservationResponse.setStartTime(adventureQuickReservation.getStartTime().toString());
        adventureQuickReservationResponse.setEndTime(adventureQuickReservation.getEndTime().toString());
        adventureQuickReservationResponse.setValidUntil(adventureQuickReservation.getValidUntil().toString());
        adventureQuickReservationResponse.setPrice(adventureQuickReservation.getPrice().toString());
        adventureQuickReservationResponse.setGuestLimit(String.valueOf(adventureQuickReservation.getGuestLimit()));
        adventureQuickReservationResponse.setUtilities(adventureUtilityMapper.mapAdventureUtilityToResponseUtility(adventureQuickReservation.getAdventureUtilities()));
        return adventureQuickReservationResponse;
    }
}
