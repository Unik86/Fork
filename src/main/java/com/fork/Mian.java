package com.fork;

import com.fork.model.Fork;
import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11529061/%D0%9C%D0%B0%D0%BB%D0%B0%D0%B3%D0%B0+v+%D0%92%D0%B8%D0%BB%D1%8C%D1%8F%D1%80%D1%80%D0%B5%D0%B0%D0%BB.html";
    public static String FAV_URL = "https://www.favbet.com/ru/live/#event=9242668";

    public static void main(String[] args) {

//        Parser will = new WillMatchParser(WILL_URL);
//        will.pars();
//        will.print();

        Parser fav = new FavMatchParser(FAV_URL);
        fav.pars();
        fav.print();

//        calc();
    }


    public static void calc(){
        Double rate1 = 2.10D;
        Double rate2 = 1.77D;
        Double allSum = 100D;

        Fork fork = new Fork(rate1, rate2, allSum);
        fork.calc();
        fork.print();
    }

}
