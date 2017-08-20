package com.fork.calc;

import com.fork.model.Bet;

import static com.fork.util.Utils.round;

public class Fork {

    private Bet bet;
    private Double allSum;

    private Double forkRate;

    private Double sumRight;
    private Double sumCenter;
    private Double sumLeft;

    private Double winSumRight;
    private Double winSumCenter;
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

    public void print(){
        System.out.println("----------");
        System.out.println("rateLeft = " + bet.getLeft());
        System.out.println("rateCenter = " + bet.getCenter());
        System.out.println("rateRight = " + bet.getRight());
        System.out.println("forkRate = " + forkRate);
        System.out.println("sumLeft = " + sumLeft + " = " + winSumLeft);
        System.out.println("sumLeft = " + sumCenter + " = " + winSumCenter);
        System.out.println("sumRight = " + sumRight + " = " + winSumRight);
        System.out.println("----------");
    }

    public boolean isHasFork(){
        return forkRate < 1.0;
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

    public Double getSumCenter() {
        return sumCenter;
    }

    public Double getWinSumCenter() {
        return winSumCenter;
    }
}
