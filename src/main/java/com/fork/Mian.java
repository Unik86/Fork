package com.fork;

import com.fork.calc.MatchService;
import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;
import com.fork.parser.WillMatchParser;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11540285/%D0%A1%D0%B5%D0%BB%D1%8C%D1%82%D0%B0+v+%D0%A0%D0%BE%D0%BC%D0%B0.html";
    public static String FAV_URL = "https://www.favbet.com/ru/live/#event=9270148";

    public static void main(String[] args) throws InterruptedException {

        System.out.println("*** Start ***");

        Parser will = new WillMatchParser(WILL_URL);
        Parser fav = new FavMatchParser(FAV_URL);
        MatchService service = new MatchService();

        while (true) {
            service.findFork(will.pars(), fav.pars());
            System.out.println("+++++");
            Thread.sleep(10000);
        }

//        System.out.println("*** End ***");
    }

}
