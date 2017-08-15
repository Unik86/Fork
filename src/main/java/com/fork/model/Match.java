package com.fork.model;

public class Match {

    private String players;
    private String url;

    private Bet winner;

    private Bet total05;
    private Bet total15;
    private Bet total25;
    private Bet total35;
    private Bet total45;
    private Bet total55;
    private Bet total65;
    private Bet total75;
    private Bet total85;
    private Bet total95;

    public Match(String players, String url) {
        this.players = players;
        this.url = url;
    }

    public String getPlayers() {
        return players;
    }

    public String getUrl() {
        return url;
    }

    public Bet getWinner() {
        return winner;
    }

    public void setWinner(Bet winner) {
        this.winner = winner;
    }

    public Bet getTotal05() {
        return total05;
    }

    public void setTotal05(Bet total05) {
        this.total05 = total05;
    }

    public Bet getTotal15() {
        return total15;
    }

    public void setTotal15(Bet total15) {
        this.total15 = total15;
    }

    public Bet getTotal25() {
        return total25;
    }

    public void setTotal25(Bet total25) {
        this.total25 = total25;
    }

    public Bet getTotal35() {
        return total35;
    }

    public void setTotal35(Bet total35) {
        this.total35 = total35;
    }

    public Bet getTotal45() {
        return total45;
    }

    public void setTotal45(Bet total45) {
        this.total45 = total45;
    }

    public Bet getTotal55() {
        return total55;
    }

    public void setTotal55(Bet total55) {
        this.total55 = total55;
    }

    public Bet getTotal65() {
        return total65;
    }

    public void setTotal65(Bet total65) {
        this.total65 = total65;
    }

    public Bet getTotal75() {
        return total75;
    }

    public void setTotal75(Bet total75) {
        this.total75 = total75;
    }

    public Bet getTotal85() {
        return total85;
    }

    public void setTotal85(Bet total85) {
        this.total85 = total85;
    }

    public Bet getTotal95() {
        return total95;
    }

    public void setTotal95(Bet total95) {
        this.total95 = total95;
    }
}
