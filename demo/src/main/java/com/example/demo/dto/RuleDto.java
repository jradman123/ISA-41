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
    private Long id;
    private String ruleDescription;
    private boolean isDeleted;
    private String cottageOwnerId;
    private String shipOwnerId;

    public RuleDto(Rules rules) {
            this.id=rules.getId();
            this.ruleDescription=rules.getRuleDescription();
            this.isDeleted=rules.isDeleted();
            this.shipOwnerId=Long.toString(rules.getShip().getId());
            this.cottageOwnerId=Long.toString(rules.getCottage().getId());

    }
}
