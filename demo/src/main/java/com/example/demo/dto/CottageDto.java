package com.example.demo.dto;

import com.example.demo.model.cottages.Cottage;

public class CottageDto {


    private Long id;
    private String name;
    private String description;
    private String address;
    private String price;
    private String image;
    private int numberOfPeople;

    public  CottageDto(Cottage cottage){
        this.id = cottage.getId();
        this.name = cottage.getName();
        this.description = cottage.getDescription();
        this.address= cottage.getAddress().toString();
       this.price = Double.toString(cottage.getPrice());
        this.numberOfPeople = cottage.getNumberOfPerson();
    }

}
