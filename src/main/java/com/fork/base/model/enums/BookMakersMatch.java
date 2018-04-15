package com.fork.base.model.enums;

public enum BookMakersMatch {

    WILL("WilliamHillMatch"),
    BWIN("BWinMatch");

    private String name;

    BookMakersMatch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
