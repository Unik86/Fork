package com.fork.finder;

import com.fork.calc.MatchService;
import com.fork.model.Match;
import com.fork.parser.FavMatchParser;
import com.fork.parser.Parser;
import com.fork.parser.WillMatchParser;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Log4j
@Service
public class FindForkServiceImpl implements FindForkService {

    @Override
    public void findFork() {

//        Parser will = new WillMatchParser();
//        Parser fav = new FavMatchParser();
//        MatchService service = new MatchService();
//
//        fav.parsAllRates();
//
//        service.findForkForMainRates(will.parsMainRates(), fav.parsMainRates());
    }

    @Override
    public List<Match> getWillMatches() {
        Parser will = new WillMatchParser();
        List<Match> matches = will.parsMainRates();
        will.closeBrowser();
        return matches;
    }

}
