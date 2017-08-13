package com.fork;

import com.fork.calc.MatchService;
import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;
import com.fork.parser.WillMatchParser;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11544169/%D0%A4%D0%B8%D0%BE%D1%80%D0%B5%D0%BD%D1%82%D0%B8%D0%BD%D0%B0+v+Parma+Calcio.html";
    public static String FAV_URL = "https://www.favbet.com/ru/live/#event=9270146";

    public static void main(String[] args) {

        System.out.println("*** Start ***");

        Parser will = new WillMatchParser(WILL_URL);
        Parser fav = new FavMatchParser(FAV_URL);

        MatchService service = new MatchService();
        service.findFork(will.pars(), fav.pars());

        System.out.println("*** End ***");
    }

}
