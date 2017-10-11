package com.fork.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.fork.util.Utils.round;

@Document(collection = "Fork")
public class Fork {

    @Getter @Setter
    private List<Match> matches;
    @Getter @Setter
    private List<Bet> bets;
    @Getter @Setter
    private String sportType;

    @Getter
    private Bet forkBet;
    @Getter
    private Double rate;
    @Getter
    private Double percent;

    public Fork() {

    }

    public Fork(Bet forkBet) {
        this.forkBet = forkBet;
    }

    public void calc(){
        if(forkBet == null || forkBet.getRight() == null || forkBet.getLeft() == null)
            return;

        // В = 1/К1 + 1/К2 + 1/К3
        if(forkBet.getCenter() != null) {
            rate = 1 / forkBet.getRight() + 1 / forkBet.getCenter() + 1 / forkBet.getLeft();
            calcPercent(rate);
            rate = round(rate, 1000);
        } else {
            rate = 1 / forkBet.getRight() + 1 / forkBet.getLeft();
            calcPercent(rate);
            rate = round(rate, 1000);
        }

        // Р = (1/К/В)*С
        /*
        sumRight =  round((1/forkBet.getRight()/ rate) * allSum);
        sumLeft =  round((1/forkBet.getLeft()/ rate) * allSum);

        winSumRight = round(sumRight * forkBet.getRight());
        winSumLeft = round(sumLeft * forkBet.getLeft());
        */
    }

    private void calcPercent(Double notRoundRate) {
        percent = (1 - notRoundRate) * 100;
        percent = round(percent, 100);
    }

    public boolean isHasFork(){
        return rate != null && rate <= 1.03;
    }

}
