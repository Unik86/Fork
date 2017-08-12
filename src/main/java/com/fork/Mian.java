package com.fork;

import com.fork.calc.Fork;
import com.fork.calc.MaxBetBuilder;
import com.fork.model.Bet;
import com.fork.model.Match;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11532049/%D0%90%D1%82%D0%BB.+%D0%9C%D0%B0%D0%B4%D1%80%D0%B8%D0%B4+%D0%AE20+v+%D0%92%D0%B0%D0%BB%D0%B5%D0%BD%D1%81%D0%B8%D1%8F+%D0%AE20.html";
    public static String FAV_URL = "https://www.favbet.com/ru/live/#event=9242668";

    public static void main(String[] args) {

//        Parser will = new WillMatchParser(WILL_URL);
//        Parser fav = new FavMatchParser(FAV_URL);
//
//        List<Map<Integer, Node>> sites = new ArrayList<>();
//        sites.add(will.pars());
//        sites.add(fav.pars());
//
//        Calc calc = new Calc();
//        calc.calcFork(sites);

//        will.print();
//        fav.print();

        testCalc();
    }

    public static void testCalc(){
        Match willMatch = new Match();

        Bet total05 = new Bet(4.75, 1.25);
        Bet total15 = new Bet(3.75, 1.5);
        willMatch.setTotal05(total05);
        willMatch.setTotal15(total15);

        Match favMatch = new Match();

        Bet total05a = new Bet(4.6, 1.42);
        Bet total15a = new Bet(3.5, 1.7);
        favMatch.setTotal05(total05a);
        favMatch.setTotal15(total15a);

        MaxBetBuilder builder = new MaxBetBuilder();

        Bet maxTotal05 = builder.calc(willMatch.getTotal05(), favMatch.getTotal05());
        Bet maxTotal15 = builder.calc(willMatch.getTotal15(), favMatch.getTotal15());

        Fork fork05 = new Fork(maxTotal05, 100D);
        fork05.calc();
        fork05.print();

        System.out.println("----------------");

        Fork fork15 = new Fork(maxTotal15, 100D);
        fork15.calc();
        fork15.print();
    }
}
