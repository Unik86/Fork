package com.fork.model;

public class Bet {

    private String name;
    private Double rate;

    public Bet(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }
}
