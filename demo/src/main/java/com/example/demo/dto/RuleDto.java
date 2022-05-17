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
    private boolean isDeleted;
    private String cottageId;
    private String shipId;

    public RuleDto(Rules rules) {
            this.id=Long.toString(rules.getId());
            this.ruleDescription=rules.getRuleDescription();
            this.isDeleted=rules.isDeleted();
            this.shipId=Long.toString(rules.getShip().getId());
            this.cottageId=Long.toString(rules.getCottage().getId());

    }
}
