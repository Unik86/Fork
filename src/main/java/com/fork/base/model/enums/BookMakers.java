package com.fork.base.model.enums;

public enum BookMakers {

    WILLIAM_HILL("WilliamHill"),
    BWIN("BWin"),
    GAME_BOOKERS("GameBookers"),
    BET365("Bet365"),
    BET10("Bet10"),
    PINNACLE("Pinnacle"),
    PARIMATCH("Parimatch"),
    UNIBET("Unibet"),
    SPORT888("Sport888");

    private String name;

    BookMakers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
