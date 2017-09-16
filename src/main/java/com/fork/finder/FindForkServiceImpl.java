package com.fork.finder;

import com.fork.model.BookMaker;
import com.fork.model.Fork;
import com.fork.calc.MatchService;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;
import com.fork.parser.Parser;
import com.fork.repository.BookMakerRepository;
import com.fork.repository.ForkRepository;
import com.fork.repository.TwoOfTnreeRepository;
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
    @Autowired
    private BookMakerRepository bookMakerRepository;
    @Autowired
    private ForkRepository forkRepository;
    @Autowired
    private TwoOfTnreeRepository twoOfTnreeRepository;

    @Override
    public void parseAll() {
        Parser will = getParser(Constants.WILL);
        will.goToSite();
        will.parsMainRates();
        will.closeBrowser();
        bookMakerRepository.save(will.getBookMaker());

        Parser bwin = getParser(Constants.BWIN);
        bwin.goToSite();
        bwin.parsMainRates();
        bwin.closeBrowser();
        bookMakerRepository.save(bwin.getBookMaker());
    }

    @Override
    public void countUp() {
        List<BookMaker> bookMakers = bookMakerRepository.findAll();
        service.findForkForMainRates(bookMakers.get(0).getMatches(), bookMakers.get(1).getMatches());

        forkRepository.save(service.getForks());
        twoOfTnreeRepository.save(service.getTwoOfThrees());
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
        log.info("matchs size = " + pars.getBookMaker().getMatches());
        return pars.getBookMaker().getMatches();
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
