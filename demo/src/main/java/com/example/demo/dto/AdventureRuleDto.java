package com.example.demo.dto;

public class AdventureRuleDto {

    private String ruleDescription;
    private String adventureId;

    public AdventureRuleDto() {
    }

    public AdventureRuleDto(String ruleDescription, String adventureId) {
        this.ruleDescription = ruleDescription;
        this.adventureId = adventureId;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getAdventureId() {
        return adventureId;
    }

    public void setAdventureId(String adventureId) {
        this.adventureId = adventureId;
    }
}
