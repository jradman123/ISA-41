package com.example.demo.dto;

import com.example.demo.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String streetName;
    private String streetNumber;
    private String city;
    private String country;

    public AddressDto(Address address){
        this.streetName = address.getStreetName();
        this.streetNumber = address.getStreetNumber();
        this.city = address.getCity();
        this.country = address.getCountry();
    }
}
