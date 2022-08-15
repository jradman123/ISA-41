package com.example.demo.dto;

import com.example.demo.model.Rules;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleDto {
    private String id;
    private String ruleDescription;
    private boolean isDeletedbyCottage;
    private boolean isDeletedbyShip;
    private Long cottageId;
    private Long shipId;

    public RuleDto(Rules rules) {
            this.id=Long.toString(rules.getId());
            this.ruleDescription=rules.getRuleDescription();
            this.isDeletedbyCottage=rules.isDeletedbyCottages();
            this.isDeletedbyShip=rules.isDeletedByShip();
            if(rules.getCottage()!=null) {
                this.cottageId = rules.getCottage().getId();
            }
            if(rules.getShip()!=null) {
                this.shipId = rules.getShip().getId();
            }


    }
}
