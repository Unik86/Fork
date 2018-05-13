package com.fork.live.model;

public enum BookMakersLive {

    WILL("WilliamHillLive"),
    BWIN("BWinLive");

    private String name;

    BookMakersLive(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
