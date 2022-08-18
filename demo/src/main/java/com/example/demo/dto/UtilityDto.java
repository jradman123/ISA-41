package com.example.demo.dto;

import com.example.demo.model.Rules;
import com.example.demo.model.Utility;

public class UtilityDto {
    public String id;
    public String name;


    public UtilityDto(Utility utility) {
        this.id=Long.toString(utility.getId());
        this.name=utility.getName();

    }
}
