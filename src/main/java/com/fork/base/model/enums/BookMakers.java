package com.fork.base.model.enums;

public enum BookMakers {

    // foreign
    WILLIAMHILL("WilliamHill"),
    BWIN("BWin"),
    GAMEBOOKERS("GameBookers"),// Copy BWIN
    BET365("Bet365"),
    BET10("Bet10"),
    PINNACLE("Pinnacle"),
    UNIBET("Unibet"),
    SPORT888("Sport888"),// Copy UNIBET
    TITANBET("TitanBet"),

    // UA
    PARIMATCH("Parimatch"),
    FANSPORT("FanSport");

    private String name;

    BookMakers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEquals(String name) {
        return this.name.equals(name);
    }
}
