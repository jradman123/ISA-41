package com.example.demo.dto;

import com.example.demo.model.cottages.Cottage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CottageDto {


    public String id;
    public String name;
    public String description;
    public String streetNumber;
    public String streetName;
    public String city;
    public String country;
    public String price;
    public String numberOfPeople;
    public String ownerEmail;

    public  CottageDto(Cottage cottage){
        this.id = Long.toString(cottage.getId());
        this.name = cottage.getName();
        this.description = cottage.getDescription();
        this.streetName= cottage.getAddress().getStreetName();
        this.streetNumber=cottage.getAddress().getStreetNumber();
        this.city=cottage.getAddress().getCity();
        this.country=cottage.getAddress().getCountry();
        this.price = Double.toString(cottage.getPrice());
        this.numberOfPeople = Integer.toString(cottage.getNumberOfPerson());
        this.ownerEmail=cottage.getCottageOwner().getEmail();
    }

}
