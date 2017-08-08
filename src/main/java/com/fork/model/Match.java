package com.fork.model;

public class Match {

    private Player one;
    private Player two;
    private String link;

    public Match(Player one, Player two) {
        this.one = one;
        this.two = two;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Player getOne() {
        return one;
    }

    public Player getTwo() {
        return two;
    }

    public String getLink() {
        return link;
    }
}
