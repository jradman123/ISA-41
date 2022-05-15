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
    public String address;
    public String price;
    public String numberOfPeople;
    private String ownerEmail;

    public  CottageDto(Cottage cottage){
        this.id = Long.toString(cottage.getId());
        this.name = cottage.getName();
        this.description = cottage.getDescription();
        this.address= cottage.getAddress().toString();
        this.price = Double.toString(cottage.getPrice());
        this.numberOfPeople = Integer.toString(cottage.getNumberOfPerson());
        this.ownerEmail=cottage.getCottageOwner().getEmail();
    }

}
