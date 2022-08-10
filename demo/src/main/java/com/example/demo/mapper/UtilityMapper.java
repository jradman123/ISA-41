package com.example.demo.mapper;

import com.example.demo.dto.AdventureUtilityDto;
import com.example.demo.dto.ResponseUtility;
import com.example.demo.model.Utility;

import java.util.HashSet;
import java.util.Set;

public class UtilityMapper {

    public Utility mapAdventureUtilityDtoToUtility(AdventureUtilityDto adventureUtilityDto){
        Utility utility = new Utility();
        utility.setName(adventureUtilityDto.getName());
        utility.setPrice(Double.parseDouble(adventureUtilityDto.getPrice()));
        return utility;
    }

    public Set<ResponseUtility> mapUtilityToResponseUtility(Set<Utility> utilities){
        Set<ResponseUtility>  responseUtilities= new HashSet<>();
        for (Utility utility: utilities) {
            responseUtilities.add(new ResponseUtility(utility.getId().toString(),utility.getName(),utility.getPrice().toString()));
        }
        return responseUtilities;
    }
}
