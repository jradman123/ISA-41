package com.example.demo.dto;

import com.example.demo.model.AdditionalService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalServiceDto {
    public Long id;
    public String name;
    public String price;

    public AdditionalServiceDto(AdditionalService as){
        this.id = as.getId();
        this.name = as.getName();
        this.price = Double.toString(as.getPrice());
    }

}
