package com.nitjsr.culfest20.models;

public class SubEvents {
    private String name, description, prize, rules,coordinators;

    public SubEvents() {
    }

    public SubEvents(String name, String description, String prize, String rules, String coordinators) {
        this.name = name;
        this.description = description;
        this.prize = prize;
        this.rules = rules;
        this.coordinators = coordinators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCoordinators() {
        return coordinators;
    }

    public void setCoordinators(String coordinators) {
        this.coordinators = coordinators;
    }
}
