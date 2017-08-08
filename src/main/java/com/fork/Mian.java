package com.fork;

import com.fork.parser.FavbetParser;
import com.fork.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Mian {

    public static void main(String[] args) {
        Parser parser = new Parser("http://sports.williamhill.com/bet/ru/betlive/24");
        parser.pars();
        parser.print();



    }

}
