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
    private Double allSum;
    @Getter
    private Double rate;

    @Getter
    private Double sumRight;
    @Getter
    private Double sumCenter;
    @Getter
    private Double sumLeft;

    @Getter
    private Double winSumRight;
    @Getter
    private Double winSumCenter;
    @Getter
    private Double winSumLeft;

    public Fork() {

    }

    public Fork(Bet forkBet) {
        this.forkBet = forkBet;
        this.allSum = 100D;
    }

    public Fork(Bet forkBet, Double allSum) {
        this.forkBet = forkBet;
        this.allSum = allSum;
    }

    public void calc(){
        if(forkBet == null || forkBet.getRight() == null || forkBet.getLeft() == null)
            return;

        // В = 1/К1 + 1/К2 + 1/К3
        if(forkBet.getCenter() != null) {
            rate =  round(1/forkBet.getRight() + 1/forkBet.getCenter() + 1/forkBet.getLeft());
            sumCenter =  round((1/forkBet.getCenter()/ rate) * allSum);
            winSumCenter = round(sumCenter * forkBet.getCenter());
        }
        else {
            rate =  round(1/forkBet.getRight() + 1/forkBet.getLeft());
        }

        // Р = (1/К/В)*С
        sumRight =  round((1/forkBet.getRight()/ rate) * allSum);
        sumLeft =  round((1/forkBet.getLeft()/ rate) * allSum);

        winSumRight = round(sumRight * forkBet.getRight());
        winSumLeft = round(sumLeft * forkBet.getLeft());
    }

    public boolean isHasFork(){
        return rate != null && rate <= 1.03;
    }

}
