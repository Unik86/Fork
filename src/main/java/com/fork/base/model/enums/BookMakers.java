package com.fork.base.model.enums;

public enum BookMakers {

    WILLIAMHILL("WilliamHill"),
    BWIN("BWin"),
//    GAMEBOOKERS("GameBookers"),
    BET365("Bet365"),
    BET10("Bet10"),
    PINNACLE("Pinnacle"),
//    PARIMATCH("Parimatch"),
    UNIBET("Unibet"),
//    SPORT888("Sport888"),
    TITANBET("TitanBet");

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
