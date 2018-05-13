package com.fork.live.model;

public enum BookMakersLive {

    WILLIAMHILL("WilliamHill"),
//    BWIN("BWinLive"),
    BET365("Bet365");

    private String name;

    BookMakersLive(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
