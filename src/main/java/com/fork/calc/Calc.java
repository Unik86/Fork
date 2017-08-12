package com.fork.calc;

import com.fork.model.Bet;
import com.fork.model.Match;
import java.util.Arrays;

public class Calc {

    public void calc(Match... matches){
        Bet maxTotal05 = new Bet(0D, 0D);

        for(Match match : Arrays.asList(matches)) {
            if(match.getTotal05().getRight() > maxTotal05.getRight()){
                maxTotal05.setRight(match.getTotal05().getRight());
            }
            if(match.getTotal05().getLeft() > maxTotal05.getLeft()){
                maxTotal05.setLeft(match.getTotal05().getLeft());
            }
        }

        Fork fork = new Fork(maxTotal05, 100D);
        fork.calc();
        fork.print();
    }

}
