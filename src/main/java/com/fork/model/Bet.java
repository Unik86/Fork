package com.fork.model;

public class Bet {

    private String name;
    private double rate;

    public Bet(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
