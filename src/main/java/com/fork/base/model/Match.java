package com.fork.base.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.fork.util.Utils.dateFormater;

@Document(collection = "Match")
public class Match {

    private String playerLeft;
    private String playerRight;
    private String bookMaker;
    private String sportType;
    private String url;
    private String time;
    private LocalDateTime parsDate = LocalDateTime.now();

    private Bet winner;

    public String getParsDateStr(){
        return dateFormater(parsDate);
    }

    public String getPlayerLeft() {
        return playerLeft;
    }

    public void setPlayerLeft(String playerLeft) {
        this.playerLeft = playerLeft;
    }

    public String getPlayerRight() {
        return playerRight;
    }

    public void setPlayerRight(String playerRight) {
        this.playerRight = playerRight;
    }

    public String getBookMaker() {
        return bookMaker;
    }

    public void setBookMaker(String bookMaker) {
        this.bookMaker = bookMaker;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDateTime getParsDate() {
        return parsDate;
    }

    public void setParsDate(LocalDateTime parsDate) {
        this.parsDate = parsDate;
    }

    public Bet getWinner() {
        return winner;
    }

    public void setWinner(Bet winner) {
        this.winner = winner;
    }
}
