package com.fork.finder;

import com.fork.model.Match;
import com.fork.parser.Parser;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class FindForkServiceImpl implements FindForkService {

    @Autowired
    private ApplicationContext appContext;

    @Override
    public void findFork() {
        Parser will = getParser("WillMatchParser");

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
    public List<Match> getMatches(String type) {
        Parser pars = getParser(type);
        return pars.getMatchs();
    }

    private Parser getParser(String type){
        return appContext.getBean(type, Parser.class);
    }

}
