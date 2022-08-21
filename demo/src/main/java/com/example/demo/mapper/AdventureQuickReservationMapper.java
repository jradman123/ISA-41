package com.example.demo.mapper;

import com.example.demo.dto.AdventureQuickReservationDto;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureQuickReservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AdventureQuickReservationMapper {

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
}
