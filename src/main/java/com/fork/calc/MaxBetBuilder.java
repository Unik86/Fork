package com.fork.calc;

import com.fork.model.Bet;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

public class MaxBetBuilder {

    public Bet calc(Bet... bets){
        return calc(Arrays.asList(bets));
    }

    public Bet calc(List<Bet> bets){
        if(bets == null)
            return null;

        Bet maxBet = new Bet();

        for(Bet bet : bets) {
            if(bet == null || bet.getLeft() == null || bet.getRight() == null)
                continue;

            if(isNull(maxBet.getLeft()) || bet.getLeft() > maxBet.getLeft()){
                maxBet.setLeft(bet.getLeft());
            }
            if(isNull(maxBet.getCenter()) || bet.getCenter() > maxBet.getCenter()){
                maxBet.setCenter(bet.getCenter());
            }
            if(isNull(maxBet.getRight()) || bet.getRight() > maxBet.getRight()){
                maxBet.setRight(bet.getRight());
            }

            if(isNull(maxBet.getName()))
                maxBet.setName(bet.getName());
        }

        return maxBet;
    }

}
