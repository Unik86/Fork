package com.fork.model;

public class Player {

    private String name;
    private int score;
    private double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getPrice() {
        return price;
    }
}
