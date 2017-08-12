package com.fork;

import com.fork.calc.Calc;
import com.fork.model.Fork;
import com.fork.model.Node;
import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;
import com.fork.parser.WillMatchParser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11532049/%D0%90%D1%82%D0%BB.+%D0%9C%D0%B0%D0%B4%D1%80%D0%B8%D0%B4+%D0%AE20+v+%D0%92%D0%B0%D0%BB%D0%B5%D0%BD%D1%81%D0%B8%D1%8F+%D0%AE20.html";
    public static String FAV_URL = "https://www.favbet.com/ru/live/#event=9242668";

    public static void main(String[] args) {

        Parser will = new WillMatchParser(WILL_URL);
        Parser fav = new FavMatchParser(FAV_URL);

        List<Map<Integer, Node>> sites = new ArrayList<>();
        sites.add(will.pars());
        sites.add(fav.pars());

        Calc calc = new Calc();
        calc.calcFork(sites);

//        will.print();
//        fav.print();

//        calc();
    }


    public static void calc(){
        Double rate1 = 4.75D;
        Double rate2 = 1.25D;
        Double allSum = 100D;

        Fork fork = new Fork(rate1, rate2, allSum);
        fork.calc();
        fork.print();
    }

}
