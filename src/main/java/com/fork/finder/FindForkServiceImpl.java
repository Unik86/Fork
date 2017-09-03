package com.fork.finder;

import com.fork.model.Match;
import com.fork.parser.Parser;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class FindForkServiceImpl implements FindForkService {

    @Autowired
    @Qualifier("MockParser")
    Parser will;

    @Override
    public void findFork() {
        will.goToSite();
        will.parsMainRates();
        will.closeBrowser();

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
        return will.getMatchs();
    }

}
