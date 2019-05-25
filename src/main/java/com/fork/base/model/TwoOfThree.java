package com.fork.base.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

import static com.fork.util.Utils.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Document(collection = "TwoOfThree")
public class TwoOfThree {

    private static final double MAX_RATE = 4.0;

    private List<Match> matches;
    private LocalDateTime parsDate = LocalDateTime.now();

    private Bet bet;
    private Double rate;

    private Bet percentBet;
    private Double sumPercentBet;
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
                && nonNull(sumPercentBet) && sumPercentBet < 105.0;
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

    public LocalDateTime getParsDate() {
        return parsDate;
    }

    public void setParsDate(LocalDateTime parsDate) {
        this.parsDate = parsDate;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
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
