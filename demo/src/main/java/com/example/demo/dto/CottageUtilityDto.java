package com.example.demo.dto;

import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.cottages.CottageUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CottageUtilityDto {

    public Long id;
    public Long cottageId;
    public Long utilityId;
    public String price;
    public String name;

    public CottageUtilityDto(CottageUtility ca) {
        this.id = ca.getId();
        this.cottageId=ca.getCottage().getId();
        this.utilityId=ca.getUtility().getId();
        this.price=ca.getPrice().toString();
    }
}
