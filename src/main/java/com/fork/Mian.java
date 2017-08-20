package com.fork;

import com.fork.calc.MatchService;
import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;
import com.fork.parser.WillMatchParser;
import org.apache.log4j.Logger;

public class Mian {

    private final static Logger logger = Logger.getLogger(Mian.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("** Start **");

        Parser fav = new FavMatchParser();
        while (true) {
            fav.parsMainRates();
            fav.print();
            Thread.sleep(10000);
        }

//        Parser fav = new FavMatchParser();
//        MatchService service = new MatchService();
//
//        while (true) {
//            service.findFork(will.pars(), fav.pars());
//            System.out.println("+++++");
//            Thread.sleep(10000);
//        }

//        logger.info("** End **");
    }

}
