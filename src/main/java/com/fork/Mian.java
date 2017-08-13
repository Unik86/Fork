package com.fork;

import com.fork.calc.Fork;
import com.fork.calc.MaxBetBuilder;
import com.fork.model.Bet;
import com.fork.model.Match;
import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;
import com.fork.parser.WillMatchParser;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11505009/%D0%A2%D0%B0%D0%BC%D0%B1%D0%BE%D0%B2+v+%D0%A0%D0%BE%D1%82%D0%BE%D1%80.html";
    public static String FAV_URL = "https://www.favbet.com/ru/live/#event=9197682";

    public static void main(String[] args) {

//        Parser will = new WillMatchParser(WILL_URL);
//        will.pars();
//        will.print();

        Parser fav = new FavMatchParser(FAV_URL);
        fav.pars();
        fav.print();

//        testCalc();
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

        //////////////////////

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
