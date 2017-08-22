package com.fork.model;

import lombok.Getter;

public class Bet {

    @Getter
    private Double left;
    @Getter
    private Double center;
    @Getter
    private Double right;

    public Bet(Double left, Double center, Double right) {
        this.left = left;
        this.center = center;
        this.right = right;
    }

    public Bet(Double left, Double right) {
        this.left = left;
        this.right = right;
    }

}
