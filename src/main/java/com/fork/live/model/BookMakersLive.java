package com.fork.live.model;

public enum BookMakersLive {

    WILL("WillFootballLive"),
    BWIN("BWinFootballLive");

    private String name;

    BookMakersLive(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
