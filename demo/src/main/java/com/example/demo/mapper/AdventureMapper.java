package com.example.demo.mapper;

import com.example.demo.dto.AdventureDto;
import com.example.demo.dto.NewAdventureDto;
import com.example.demo.model.Address;
import com.example.demo.model.adventures.Adventure;
import org.springframework.stereotype.Component;

@Component
public class AdventureMapper {

    public Adventure mapNewAdventureDtoToAdventure(NewAdventureDto newAdventureDto) {
        Adventure adventure = new Adventure();
        Address address = new Address(newAdventureDto.getStreetName(), newAdventureDto.getStreetNumber(),
                newAdventureDto.getCity(), newAdventureDto.getCountry());
        adventure.setAddress(address);
        adventure.setPrice(Double.parseDouble(newAdventureDto.getPrice()));
        adventure.setCancellationConditions(Double.parseDouble(newAdventureDto.getCancellationConditions()));
        adventure.setName(newAdventureDto.getName());
        adventure.setDescription(newAdventureDto.getDescription());
        adventure.setGuestLimit(Integer.parseInt(newAdventureDto.getGuestLimit()));
        return adventure;
    }

    public AdventureDto mapAdventureToAdventureDto(Adventure adventure) {
        AdventureDto adventureDto = new AdventureDto();
        adventureDto.setId(adventure.getId().toString());
        adventureDto.setName(adventure.getName());
        adventureDto.setDescription(adventure.getDescription());
        adventureDto.setCancellationConditions(adventure.getCancellationConditions().toString());
        adventureDto.setGuestLimit(adventure.getGuestLimit().toString());
        adventureDto.setCity(adventure.getAddress().getCity());
        adventureDto.setCountry(adventure.getAddress().getCountry());
        adventureDto.setStreetName(adventure.getAddress().getStreetName());
        adventureDto.setStreetNumber(adventure.getAddress().getStreetNumber());
        adventureDto.setPrice(adventure.getPrice().toString());
        return adventureDto;
    }

    public Adventure mapAdventureDtoToAdventure(AdventureDto adventureDto) {
        Adventure adventure = new Adventure();
        Address address = new Address(adventureDto.getStreetName(), adventureDto.getStreetNumber(),
                adventureDto.getCity(), adventureDto.getCountry());
        adventure.setAddress(address);
        adventure.setPrice(Double.parseDouble(adventureDto.getPrice()));
        adventure.setCancellationConditions(Double.parseDouble(adventureDto.getCancellationConditions()));
        adventure.setName(adventureDto.getName());
        adventure.setDescription(adventureDto.getDescription());
        adventure.setGuestLimit(Integer.parseInt(adventureDto.getGuestLimit()));
        return adventure;
    }
}