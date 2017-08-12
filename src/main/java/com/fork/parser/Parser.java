package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Node;

import java.util.HashMap;
import java.util.Map;

import static com.fork.util.Utils.fixLengthStr;

public interface Parser {

    Map<Integer, Node> nodes = new HashMap<>();

    public Map<Integer, Node> pars();

    public default void print() {
        for (Map.Entry<Integer, Node> node : nodes.entrySet()) {
            System.out.println();
            System.out.println("*** " + node.getValue().getName() + " ***");
            System.out.println();

            for (Bet bet : node.getValue().getBets()) {
                System.out.print(fixLengthStr(bet.getName() + " * " + Double.toString(bet.getRate()), 20));
                System.out.print(" | ");
            }

            System.out.println();
        }
    }

}
