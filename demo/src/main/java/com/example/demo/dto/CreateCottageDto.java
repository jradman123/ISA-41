package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCottageDto {

    private String name;
    private String description;
    private AddressDto address;
    private Double price;
    private String ownerEmail;

}
