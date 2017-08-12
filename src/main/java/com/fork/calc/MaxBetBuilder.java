package com.fork.calc;

import com.fork.model.Bet;
import com.fork.model.Match;
import java.util.Arrays;

public class MaxBetBuilder {

    public Bet calc(Bet... bets){
        Bet maxBet = new Bet(0D, 0D);

        for(Bet bet : Arrays.asList(bets)) {
            if(bet.getRight() > maxBet.getRight()){
                maxBet.setRight(bet.getRight());
            }
            if(bet.getLeft() > maxBet.getLeft()){
                maxBet.setLeft(bet.getLeft());
            }
        }

        return maxBet;
    }

}
