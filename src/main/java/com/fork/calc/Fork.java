package com.fork.calc;

import com.fork.model.Bet;

import static com.fork.util.Utils.round;

public class Fork {

    private Bet bet;
    private Double allSum;

    private Double forkRate;
    private Double sumRight;
    private Double sumLeft;
    private Double winSumRight;
    private Double winSumLeft;

    public Fork(Bet bet, Double allSum) {
        this.bet = bet;
        this.allSum = allSum;
    }

    public void calc(){
        // В = 1/К1 + 1/К2 + 1/К3
        forkRate =  round(1/bet.getRight() + 1/bet.getLeft());
        // Р = (1/К/В)*С
        sumRight =  round((1/bet.getRight()/forkRate) * allSum);
        sumLeft =  round((1/bet.getLeft()/forkRate) * allSum);

        winSumRight = round(sumRight * bet.getRight());
        winSumLeft = round(sumLeft * bet.getLeft());
    }

    public void print(){
        System.out.println("rateRight = " + bet.getRight());
        System.out.println("rateLeft = " + bet.getLeft());
        System.out.println("forkRate = " + forkRate);
        System.out.println("sumRight = " + sumRight + " = " + winSumRight);
        System.out.println("sumLeft = " + sumLeft + " = " + winSumLeft);
    }

    public Double getForkRate() {
        return forkRate;
    }

    public Double getSumRight() {
        return sumRight;
    }

    public Double getSumLeft() {
        return sumLeft;
    }

    public Double getWinSumRight() {
        return winSumRight;
    }

    public Double getWinSumLeft() {
        return winSumLeft;
    }
}
