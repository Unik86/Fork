package com.fork.finder;

import com.fork.calc.Fork;
import com.fork.calc.MatchServiceImpl;
import com.fork.model.Match;
import com.fork.parser.Parser;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class FindForkServiceImpl implements FindForkService {

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private MatchServiceImpl service;

    @Override
    public void findFork() {
        Parser will = getParser("WillMatchParser");
        Parser bwin = getParser("BWinMatchParser");

        will.goToSite();
        will.parsMainRates();
        will.closeBrowser();

        bwin.goToSite();
        bwin.parsMainRates();
        bwin.closeBrowser();

        service.findForkForMainRates(will.parsMainRates(), bwin.parsMainRates());
    }

    @Override
    public List<Match> getMatches(String type) {
        Parser pars = getParser(type);
        return pars.getMatchs();
    }

    @Override
    public List<Fork> getForks(){
        return service.getForks();
    }

    private Parser getParser(String type){
        return appContext.getBean(type, Parser.class);
    }

}
