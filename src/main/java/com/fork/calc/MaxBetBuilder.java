package com.fork.calc;

import com.fork.model.Bet;

import java.util.Arrays;
import java.util.List;

public class MaxBetBuilder {

    public Bet calc(Bet... bets){
        return calc(Arrays.asList(bets));
    }

    public Bet calc(List<Bet> bets){
        if(bets == null)
            return null;

        Bet maxBet = new Bet();
        maxBet.setLeft(0.0);
        maxBet.setCenter(0.0);
        maxBet.setRight(0.0);

        for(Bet bet : bets) {
            if(bet == null || bet.getLeft() == null || bet.getRight() == null)
                continue;

            if(bet.getLeft() > maxBet.getLeft()){
                maxBet.setLeft(bet.getLeft());
            }
            if(bet.getCenter() != null && bet.getCenter() > maxBet.getCenter()){
                maxBet.setCenter(bet.getCenter());
            }
            if(bet.getRight() > maxBet.getRight()){
                maxBet.setRight(bet.getRight());
            }
        }

        return maxBet;
    }

}
