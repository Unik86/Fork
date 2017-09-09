package com.fork.finder;

import com.fork.model.Fork;
import com.fork.calc.MatchService;
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
    private MatchService service;

    @Override
    public void findFork() {
        Parser will = getParser("WillMatchParser");
        will.goToSite();
        will.parsMainRates();
        will.closeBrowser();

        Parser bwin = getParser("BWinMatchParser");
        bwin.goToSite();
        bwin.parsMainRates();
        bwin.closeBrowser();

        service.findForkForMainRates(will.getMatchs(), bwin.getMatchs());
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
