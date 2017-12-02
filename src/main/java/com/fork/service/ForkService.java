package com.fork.service;

import com.fork.model.BookMaker;
import com.fork.model.Fork;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;
import com.fork.model.enums.SportTypes;
import com.fork.parser.Parser;
import com.fork.repository.BookMakerRepository;
import com.fork.repository.ForkRepository;
import com.fork.repository.TwoOfThreeRepository;
import com.fork.model.enums.BookMakers;
import com.fork.thread.RunParser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Log4j
@Service
public class ForkService {

    @Getter @Setter
    private String sportType = SportTypes.TENNIS.getType();

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private FindForkService findForkService;
    @Autowired
    private BookMakerRepository bookMakerRepository;
    @Autowired
    private ForkRepository forkRepository;
    @Autowired
    private TwoOfThreeRepository twoOfTnreeRepository;

    public void parseAll() {
        for(BookMakers bookMaker : BookMakers.values()){
            parseBookMaker(bookMaker.getName());
        }
    }

    public void parseBookMaker(String bookMaker) {
        Parser parser = getParser(bookMaker);
        RunParser runParser = new RunParser(parser, bookMakerRepository);
        Thread thread = new Thread(runParser);
        thread.start();
    }

    public void countUp() {
        List<BookMaker> bookMakers = bookMakerRepository.findBySportType(sportType);
        ForkResult forkResult = findForkService.findForkForMainRates(bookMakers);

        forkRepository.deleteBySportType(sportType);
        forkRepository.save(forkResult.getForks());

        twoOfTnreeRepository.deleteAll();
        twoOfTnreeRepository.save(forkResult.getTwoOfThrees());
    }

    public void findMatchFork(Fork fork) {
//        Parser will = getParser(BookMakers.WILL.getName());
//        will.parsAllRates();
//        Parser bwin = getParser(BookMakers.BWIN.getName());
//        bwin.parsAllRates();
    }

    public List<Match> getMatches(String name) {
        BookMaker bookMaker = bookMakerRepository.findOneByNameAndSportType(name, sportType);

        log.info("matches size = " + getMatchSize(bookMaker));
        return isNull(bookMaker) ? null : bookMaker.getMatches();
    }

    public List<Fork> getForks(){
        List<Fork> forks = forkRepository.findBySportTypeOrderByRate(sportType);

        log.info("forks size = " + (isNull(forks) ? 0 : forks.size()));
        return forks;
    }

    public List<TwoOfThree> getTwoOfThrees(){
        List<TwoOfThree> twoOfThrees = twoOfTnreeRepository.findAllByOrderBySumPercentBet();

        log.info("twoOfThrees size = " + (isNull(twoOfThrees) ? 0 : twoOfThrees.size()));
        return twoOfThrees;
    }

    private Parser getParser(String bookMaker){
        return appContext.getBean(bookMaker + sportType, Parser.class);
    }

    private int getMatchSize(BookMaker bookMaker){
        if(isNull(bookMaker) || isNull(bookMaker.getMatches()))
            return 0;
        else
            return bookMaker.getMatches().size();
    }
}
