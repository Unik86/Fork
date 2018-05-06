package com.fork.base.model.enums;

public enum BookMakers {

    WILL("WilliamHill"),
    BWIN("BWin"),
    BET365("Bet365"),
    BET10("Bet10"),
    PINNACLE("Pinnacle"),
//    PARIMATCH("Parimatch"),
    UNIBET("Unibet");

    private String name;

    BookMakers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
