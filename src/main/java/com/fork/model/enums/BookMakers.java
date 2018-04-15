package com.fork.model.enums;

public enum BookMakers {

    WILL("WilliamHill"),
    BWIN("BWin"),
    BET365("Bet365"),
    PINNACLE("Pinnacle"),
    UNIBET("Unibet");

    private String name;

    BookMakers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
