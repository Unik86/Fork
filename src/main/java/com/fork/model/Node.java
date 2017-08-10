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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (name != null ? !name.equals(node.name) : node.name != null) return false;
        return bets != null ? bets.equals(node.bets) : node.bets == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (bets != null ? bets.hashCode() : 0);
        return result;
    }
}
