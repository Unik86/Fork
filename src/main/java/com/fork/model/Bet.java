package com.fork.model;

public class Bet {

    private Double left;
    private Double center;
    private Double right;

    public Bet(Double left, Double center, Double right) {
        this.left = left;
        this.center = center;
        this.right = right;
    }

    public Bet(Double right, Double left) {
        this.right = right;
        this.left = left;
    }

    public Double getLeft() {
        return left;
    }

    public Double getCenter() {
        return center;
    }

    public Double getRight() {
        return right;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public void setCenter(Double center) {
        this.center = center;
    }

    public void setRight(Double right) {
        this.right = right;
    }
}
