package com.fork.model;

public enum BookMakersMatch {

    WILL("WilliamHillMatch");

    private String name;

    BookMakersMatch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
