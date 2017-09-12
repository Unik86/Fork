package com.fork.finder;

import com.fork.model.Fork;
import com.fork.calc.MatchService;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;
import com.fork.parser.Parser;
import com.fork.util.Constants;
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
        Parser will = getParser(Constants.WILL);
        will.goToSite();
        will.parsMainRates();
        will.closeBrowser();

        Parser bwin = getParser(Constants.BWIN);
        bwin.goToSite();
        bwin.parsMainRates();
        bwin.closeBrowser();

        service.findForkForMainRates(will.getMatchs(), bwin.getMatchs());
    }

    @Override
    public void findMatchFork(Fork fork) {
        Parser will = getParser(Constants.WILL);
//        will.parsAllRates();

        Parser bwin = getParser(Constants.BWIN);
//        bwin.parsAllRates();
    }

    @Override
    public List<Match> getMatches(String type) {
        Parser pars = getParser(type);
        log.info("matchs size = " + pars.getMatchs());
        return pars.getMatchs();
    }

    @Override
    public List<Fork> getForks(){
        return service.getForks();
    }

    @Override
    public List<TwoOfThree> getTwoOfThrees(){
        return service.getTwoOfThrees();
    }

    private Parser getParser(String type){
        return appContext.getBean(type, Parser.class);
    }

}
