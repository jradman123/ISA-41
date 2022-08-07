package com.example.demo.model.adventures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "adventureRules")
public class AdventureRule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ruleDescription", nullable = false)
    private String ruleDescription;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    public AdventureRule() {
    }

    public AdventureRule(Long id, String ruleDescription, boolean isDeleted) {
        this.id = id;
        this.ruleDescription = ruleDescription;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
