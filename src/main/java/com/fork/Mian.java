package com.fork;

import com.fork.calc.MatchService;
import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;
import com.fork.parser.WillMatchParser;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.Levenshtein;
import org.apache.log4j.Logger;

import static java.awt.SystemColor.info;

public class Mian {

    private final static Logger logger = Logger.getLogger(Mian.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("** Start **");

//        JaroWinkler l = new JaroWinkler();
//        System.out.println(l.similarity("Qingdao Jonoon", "Qingdao Jonoon"));


        Parser will = new WillMatchParser();
        Parser fav = new FavMatchParser();
        MatchService service = new MatchService();

//        while (true) {
            service.findForkForMainRates(will.parsMainRates(), fav.parsMainRates());
//            System.out.println("+++++");
//            Thread.sleep(10000);
//        }

//        logger.info("** End **");
    }

}
