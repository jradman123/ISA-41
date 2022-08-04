package com.example.demo.mapper;

import com.example.demo.dto.NewAdventureDto;
import com.example.demo.model.Address;
import com.example.demo.model.adventures.Adventure;
import org.springframework.stereotype.Component;

@Component
public class AdventureMapper {

    /*public Adventure mapAdventureDtoToAdventure(NewAdventureDto newAdventureDto){
        Adventure adventure = new Adventure();
        Address address = new Address(newAdventureDto.getStreetName(),newAdventureDto.getStreetNumber(),
                                        newAdventureDto.getCity(),newAdventureDto.getCountry());
        adventure.setAddress(address);
        adventure.setPrice(Double.parseDouble(newAdventureDto.getPrice()));
        adventure.setCancellationConditions(Double.parseDouble(newAdventureDto.getCancellationConditions()));
        adventure.setName(newAdventureDto.getName());
        adventure.setDescription(newAdventureDto.getDescription());
        adventure.setInstructor();
        return adventure;
    }*/
}
