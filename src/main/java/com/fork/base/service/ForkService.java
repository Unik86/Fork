package com.fork.base.service;

import com.fork.base.model.*;
import com.fork.base.model.enums.ParseType;
import com.fork.base.model.enums.SportTypes;
import com.fork.base.parser.Parser;
import com.fork.base.repository.BookMakerRepository;
import com.fork.base.repository.ForkRepository;
import com.fork.base.repository.TwoOfThreeRepository;
import com.fork.base.model.enums.BookMakers;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.fork.base.model.enums.BookMakersGroups.ALEX_BOOKMAKERS;
import static com.fork.base.model.enums.BookMakersGroups.MY_BOOKMAKERS;
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

    public void clearAll() {
        bookMakerRepository.deleteAll();
        forkRepository.deleteAll();
    }

    public List<ParseResult> parseAll(String group, String parseType) {
        switch(group) {
            case "alex":
                return parseGroupOfBookMaker(ALEX_BOOKMAKERS, parseType);
            case "my":
                return parseGroupOfBookMaker(MY_BOOKMAKERS, parseType);
            default:
                return null;
        }
    }

    private List<ParseResult> parseGroupOfBookMaker(Collection<BookMakers> bookMakers, String parseType) {
        return bookMakers.stream()
                .map(bm -> parseBookMaker(bm.getName(), parseType))
                .collect(Collectors.toList());
    }

    public ParseResult parseBookMaker(String bookMakerName, String parseType) {
        if(ParseType.ONLY_LIVE.isEquals(parseType)
                && !BookMakers.WILLIAMHILL.isEquals(bookMakerName)
                && !BookMakers.FANSPORT.isEquals(bookMakerName)
                && !BookMakers.BET365.isEquals(bookMakerName)) {
            return null;
        }

        log.info("Run parser >>>> " + bookMakerName);

        try {
            Parser parser = getParser(bookMakerName);
            parser.setParseType(parseType);
            parser.goToSite();
            parser.parsMainRates();
            parser.closeBrowser();

            BookMaker bookMaker = parser.getBookMaker();

            bookMakerRepository.deleteByNameAndSportType(
                    bookMaker.getName(),
                    bookMaker.getSportType()
            );

            bookMakerRepository.save(bookMaker);

            log.info("Finish parser >>>>>>>>>> " + bookMakerName);

            return new ParseResult(
                    bookMakerName,
                    String.valueOf(bookMaker.getMatches().size())
            );
        } catch (Exception e){
            log.error("Error parser >>>>>>>>>> " + e);

            return new ParseResult(bookMakerName,"Error");
        }
    }

    public String countUp() {
        try {
            List<BookMaker> bookMakers = bookMakerRepository.findBySportType(sportType);
            ForkResult forkResult = findForkService.findForkForMainRates(bookMakers);

            forkRepository.deleteBySportType(sportType);
            forkRepository.save(forkResult.getForks());

            twoOfTnreeRepository.deleteAll();
            twoOfTnreeRepository.save(forkResult.getTwoOfThrees());

            return "Count up success!";
        } catch (Exception e) {
            return "Count up Error!";
        }
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
