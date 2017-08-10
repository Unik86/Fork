package com.fork;

import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;
import com.fork.parser.WillMatchParser;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11262526/%D0%91%D0%B5%D1%80%D0%B8+v+%D0%A1%D0%B0%D0%BD%D0%B4%D0%B5%D1%80%D0%BB%D0%B5%D0%BD%D0%B4.html";
    public static String FAV_URL = "https://www.favbet.com/ru/live/#event=9235378";

    public static void main(String[] args) {

        Parser will = new WillMatchParser(WILL_URL);
        will.pars();
        will.print();

//        Parser fav = new FavMatchParser(FAV_URL);
//        fav.pars();
//        fav.print();

    }

}
