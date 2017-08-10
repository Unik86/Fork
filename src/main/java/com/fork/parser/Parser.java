package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Node;

import java.util.HashMap;
import java.util.Map;

import static com.fork.util.Utils.fixLengthStr;

public interface Parser {

    Map<Integer, Node> nodes = new HashMap<>();

    public void pars();

    public default void print() {
        nodes.forEach((k,v) -> {
            System.out.println();
            System.out.println("*** " + v.getName() + " ***");
            System.out.println();

            for (Bet bet : v.getBets()) {
                System.out.print(fixLengthStr(bet.getName() + " * " + Double.toString(bet.getRate()), 20));
                System.out.print(" | ");
            }

            System.out.println();
        });
    }

}
