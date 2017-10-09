package com.fork.finder;

import com.fork.model.BookMaker;
import com.fork.model.Fork;
import com.fork.calc.MatchService;
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

@Log4j
@Service
public class FindForkService {

    @Getter @Setter
    private String sportType = SportTypes.TENNIS.getType();

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private MatchService matchService;
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
        matchService.findForkForMainRates(bookMakers);

        forkRepository.deleteBySportType(sportType);
        forkRepository.save(matchService.getForks());

        twoOfTnreeRepository.deleteAll();
        twoOfTnreeRepository.save(matchService.getTwoOfThrees());
    }

    public void findMatchFork(Fork fork) {
//        Parser will = getParser(BookMakers.WILL.getName());
//        will.parsAllRates();
//        Parser bwin = getParser(BookMakers.BWIN.getName());
//        bwin.parsAllRates();
    }

    public List<Match> getMatches(String name) {
        BookMaker bookMaker = bookMakerRepository.findOneByNameAndSportType(name, sportType);

        log.info("matches size = " + bookMaker.getMatches().size());
        return bookMaker.getMatches();
    }

    public List<Fork> getForks(){
        List<Fork> forks = forkRepository.findBySportTypeOrderByRate(sportType);

        log.info("forks size = " + forks.size());
        return forks;
    }

    public List<TwoOfThree> getTwoOfThrees(){
        List<TwoOfThree> twoOfThrees = twoOfTnreeRepository.findAllByOrderByRate();

        log.info("twoOfThrees size = " + twoOfThrees.size());
        return twoOfThrees;
    }

    private Parser getParser(String bookMaker){
        return appContext.getBean(bookMaker + sportType, Parser.class);
    }

}
