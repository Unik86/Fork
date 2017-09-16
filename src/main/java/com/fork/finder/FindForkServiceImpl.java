package com.fork.finder;

import com.fork.model.BookMaker;
import com.fork.model.Fork;
import com.fork.calc.MatchService;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;
import com.fork.parser.Parser;
import com.fork.repository.BookMakerRepository;
import com.fork.repository.ForkRepository;
import com.fork.repository.TwoOfThreeRepository;
import com.fork.model.BookMakers;
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
    private TwoOfThreeRepository twoOfTnreeRepository;

    @Override
    public void parseAll() {
        for(BookMakers bookMaker : BookMakers.values()){
            Parser parser = getParser(bookMaker.getName());
            parser.goToSite();
            parser.parsMainRates();
            parser.closeBrowser();
            bookMakerRepository.save(parser.getBookMaker());
        }
    }

    @Override
    public void countUp() {
        List<BookMaker> bookMakers = bookMakerRepository.findAll();
        service.findForkForMainRates(bookMakers);

        forkRepository.deleteAll();
        forkRepository.save(service.getForks());

        twoOfTnreeRepository.deleteAll();
        twoOfTnreeRepository.save(service.getTwoOfThrees());
    }

    @Override
    public void findMatchFork(Fork fork) {
        Parser will = getParser(BookMakers.WILL.getName());
//        will.parsAllRates();

        Parser bwin = getParser(BookMakers.BWIN.getName());
//        bwin.parsAllRates();
    }

    @Override
    public List<Match> getMatches(String type) {
        BookMaker bookMaker = bookMakerRepository.findOne(type);

        log.info("matches size = " + bookMaker.getMatches().size());
        return bookMaker.getMatches();
    }

    @Override
    public List<Fork> getForks(){
        List<Fork> forks = forkRepository.findAll();

        log.info("forks size = " + forks.size());
        return forks;
    }

    @Override
    public List<TwoOfThree> getTwoOfThrees(){
        List<TwoOfThree> twoOfThrees = twoOfTnreeRepository.findAll();

        log.info("twoOfThrees size = " + twoOfThrees.size());
        return twoOfThrees;
    }

    private Parser getParser(String type){
        return appContext.getBean(type, Parser.class);
    }

}
