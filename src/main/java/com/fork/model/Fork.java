package com.fork.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.fork.util.Utils.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
    private Bet percentBet;
    @Getter
    private Double sumPercentBet;
    @Getter
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
        return nonNull(rate) && rate <= 1.03;
    }

}
