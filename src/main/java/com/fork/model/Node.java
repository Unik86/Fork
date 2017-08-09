package com.fork.model;

import java.util.List;

public class Node {

    private String name;

    private List<Bet> bets;

    public Node(String name, List<Bet> bets) {
        this.name = name;
        this.bets = bets;
    }

    public String getName() {
        return name;
    }

    public List<Bet> getBets() {
        return bets;
    }

}
