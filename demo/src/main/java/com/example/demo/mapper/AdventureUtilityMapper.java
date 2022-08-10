package com.example.demo.mapper;

import com.example.demo.dto.AdventureUtilityDto;
import com.example.demo.dto.ResponseUtility;
import com.example.demo.model.adventures.AdventureUtility;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AdventureUtilityMapper {

    public AdventureUtility mapAdventureUtilityDtoToAdventureUtility(AdventureUtilityDto adventureUtilityDto){
        AdventureUtility adventureUtility = new AdventureUtility();
        adventureUtility.setName(adventureUtilityDto.getName());
        adventureUtility.setPrice(Double.parseDouble(adventureUtilityDto.getPrice()));
        return adventureUtility;
    }

    public Set<ResponseUtility> mapAdventureUtilityToResponseUtility(Set<AdventureUtility> utilities){
        Set<ResponseUtility>  responseUtilities= new HashSet<>();
        for (AdventureUtility utility: utilities) {
            responseUtilities.add(new ResponseUtility(utility.getId().toString(),utility.getName(),utility.getPrice().toString()));
        }
        return responseUtilities;
    }
}
