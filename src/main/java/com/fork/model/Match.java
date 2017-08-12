package com.fork.model;

public class Match {

    private Bet winner;
    private Bet total05;
    private Bet total15;

    public Bet getWinner() {
        return winner;
    }

    public Bet getTotal05() {
        return total05;
    }

    public Bet getTotal15() {
        return total15;
    }

    public void setWinner(Bet winner) {
        this.winner = winner;
    }

    public void setTotal05(Bet total05) {
        this.total05 = total05;
    }

    public void setTotal15(Bet total15) {
        this.total15 = total15;
    }
}
