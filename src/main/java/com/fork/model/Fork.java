package com.fork.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.fork.util.Utils.round;

@Document(collection = "Fork")
public class Fork {

    @Id
    @Getter @Setter
    private long id;

    @Getter @Setter
    private List<Match> matches;
    @Getter
    private Bet bet;
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
            rate =  round(1/bet.getRight() + 1/bet.getCenter() + 1/bet.getLeft());
            sumCenter =  round((1/bet.getCenter()/ rate) * allSum);
            winSumCenter = round(sumCenter * bet.getCenter());
        }
        else {
            rate =  round(1/bet.getRight() + 1/bet.getLeft());
        }

        // Р = (1/К/В)*С
        sumRight =  round((1/bet.getRight()/ rate) * allSum);
        sumLeft =  round((1/bet.getLeft()/ rate) * allSum);

        winSumRight = round(sumRight * bet.getRight());
        winSumLeft = round(sumLeft * bet.getLeft());
    }

    public boolean isHasFork(){
        return rate != null && rate < 1.05;
    }

}
