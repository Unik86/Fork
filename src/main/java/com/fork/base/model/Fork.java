package com.fork.base.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

import static com.fork.util.Utils.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Document(collection = "Fork")
public class Fork {

    private List<Match> matches;
    private List<Bet> bets;
    private String sportType;
    private LocalDateTime parsDate;

    private Bet forkBet;
    private Double rate;

    private Bet percentBet;
    private Double sumPercentBet;
    private Double percent;

    public Fork() {
    }

    public Fork(Bet forkBet) {
        this.forkBet = forkBet;
    }

    public void calc(){
        if(isNull(forkBet) || isNull(forkBet.getRight()) || isNull(forkBet.getLeft()))
            return;

        percentBet = new Bet();

        // В = 1/К1 + 1/К2 + 1/К3
        rate = 1 / forkBet.getRight()
                + (isNull(forkBet.getCenter()) ? 0 : 1 / forkBet.getCenter())
                + 1 / forkBet.getLeft();

        percentBet.setLeft(calcPercentBet(forkBet.getLeft()));
        percentBet.setCenter(calcPercentBet(forkBet.getCenter()));
        percentBet.setRight(calcPercentBet(forkBet.getRight()));

        percent =  round(
                ((1/forkBet.getRight()/ rate) * 100) * forkBet.getRight() - 100,
                100
        );
        rate = round(rate, 1000);

        sumPercentBet = percentBet.getLeft()
                + notNullDouble(percentBet.getCenter())
                + percentBet.getRight();
        sumPercentBet = round(sumPercentBet, 100);
    }

    public boolean isHasFork(){
        return nonNull(rate) && rate <= 1.02;
    }

    public String getParsDateStr(){
        return dateFormater(parsDate);
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public LocalDateTime getParsDate() {
        return parsDate;
    }

    public void setParsDate(LocalDateTime parsDate) {
        this.parsDate = parsDate;
    }

    public Bet getForkBet() {
        return forkBet;
    }

    public void setForkBet(Bet forkBet) {
        this.forkBet = forkBet;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Bet getPercentBet() {
        return percentBet;
    }

    public void setPercentBet(Bet percentBet) {
        this.percentBet = percentBet;
    }

    public Double getSumPercentBet() {
        return sumPercentBet;
    }

    public void setSumPercentBet(Double sumPercentBet) {
        this.sumPercentBet = sumPercentBet;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
