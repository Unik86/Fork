package com.fork.model;

import static com.fork.util.Utils.round;

public class Fork {

    private Double rate1;
    private Double rate2;
    private Double forkRate;
    private Double sum1;
    private Double sum2;
    private Double allSum;
    private Double winSum1;
    private Double winSum2;

    public Fork(Double rate1, Double rate2, Double allSum) {
        this.rate1 = rate1;
        this.rate2 = rate2;
        this.allSum = allSum;
    }

    public void calc(){
        // В = 1/К1 + 1/К2 + 1/К3
        forkRate =  round(1/rate1 + 1/rate2);
        // Р = (1/К/В)*С
        sum1 =  round((1/rate1/forkRate) * allSum);
        sum2 =  round((1/rate2/forkRate) * allSum);

        winSum1 = round(sum1 * rate1);
        winSum2 = round(sum2 * rate2);
    }

    public void print(){
        System.out.println("rate1 = " + rate1);
        System.out.println("rate2 = " + rate2);
        System.out.println("forkRate = " + forkRate);
        System.out.println("sum1 = " + sum1 + " = " + winSum1);
        System.out.println("sum2 = " + sum2 + " = " + winSum2);
    }

}
