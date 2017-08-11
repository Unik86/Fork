package com.fork;

import com.fork.model.Fork;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11529279/Atletico+Guanare+v+Club+Deportivo+Lara.html";
    public static String FAV_URL = "https://www.favbet.com/ru/live/#event=9235378";

    public static void main(String[] args) {

//        Parser will = new WillMatchParser(WILL_URL);
//        will.pars();
//        will.print();

//        Parser fav = new FavMatchParser(FAV_URL);
//        fav.pars();
//        fav.print();

        calc();
    }


    public static void calc(){
        Double rate1 = 2.9D;
        Double rate2 = 1.61D;
        Double allSum = 100D;

        Fork fork = new Fork(rate1, rate2, allSum);
        fork.calc();
        fork.print();
    }

}
