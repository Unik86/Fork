package com.fork.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fork.util.Utils.round;

public class Fork {

    @Getter @Setter
    private List<Match> matches;
    @Getter
    private Bet bet;
    @Getter
    private Double allSum;
    @Getter
    private Double forkRate;

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

    public Fork(Bet bet) {
        this.bet = bet;
        this.allSum = 100D;
    }

    public Fork(Bet bet, Double allSum) {
        this.bet = bet;
        this.allSum = allSum;
    }

    public void calc(){
        if(bet == null || bet.getRight() == null || bet.getLeft() == null)
            return;

        // В = 1/К1 + 1/К2 + 1/К3
        if(bet.getCenter() != null) {
            forkRate =  round(1/bet.getRight() + 1/bet.getCenter() + 1/bet.getLeft());
            sumCenter =  round((1/bet.getCenter()/forkRate) * allSum);
            winSumCenter = round(sumCenter * bet.getCenter());
        }
        else {
            forkRate =  round(1/bet.getRight() + 1/bet.getLeft());
        }

        // Р = (1/К/В)*С
        sumRight =  round((1/bet.getRight()/forkRate) * allSum);
        sumLeft =  round((1/bet.getLeft()/forkRate) * allSum);

        winSumRight = round(sumRight * bet.getRight());
        winSumLeft = round(sumLeft * bet.getLeft());
    }

    public boolean isHasFork(){
        return forkRate != null && forkRate < 1.05;
    }

}
