package com.fork.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.fork.util.Utils.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Document(collection = "TwoOfThree")
public class TwoOfThree {

    private static final double MAX_RATE = 4.0;

    @Getter @Setter
    private List<Match> matches;

    @Getter
    private Bet bet;
    @Getter
    private Double rate;

    @Getter
    private Bet percentBet;
    @Getter
    private Double sumPercentBet;
    @Getter
    private Double percent;

    public TwoOfThree() {

    }

    public TwoOfThree(Bet bet) {
        this.bet = bet;
    }

    public void calc() {
        if (isNull(bet) || isNull(bet.getRight()) || isNull(bet.getLeft()))
            return;

        if (bet.getLeft() > MAX_RATE
                && bet.getLeft() > bet.getCenter()
                && bet.getLeft() > bet.getRight())
            calcRate(bet.getRight(), bet.getCenter());
        else if (bet.getRight() > MAX_RATE
                && bet.getRight() > bet.getCenter()
                && bet.getRight() > bet.getLeft())
            calcRate(bet.getLeft(), bet.getCenter());
        else if (bet.getCenter() > MAX_RATE
                && bet.getCenter() > bet.getRight()
                && bet.getCenter() > bet.getLeft())
            calcRate(bet.getLeft(), bet.getRight());
    }

    private void calcRate(Double one, Double two){
        percentBet = new Bet();

        // В = 1/К1 + 1/К2
        rate = 1 / one + 1 / two;

        percent =  round(
                ((1/one/ rate) * 100) * one - 100,
                100
        );
        rate = round(rate, 1000);

        percentBet.setLeft(calcPercentBet(bet.getLeft()));
        percentBet.setCenter(calcPercentBet(bet.getCenter()));
        percentBet.setRight(calcPercentBet(bet.getRight()));

        sumPercentBet = percentBet.getLeft()
                + notNullDouble(percentBet.getCenter())
                + percentBet.getRight();
        sumPercentBet = round(sumPercentBet, 100);
    }

    public boolean isHasGoodRate(){
        return nonNull(percent) && percent > 10.0
                && nonNull(sumPercentBet) && sumPercentBet < 110.0;
    }

}
