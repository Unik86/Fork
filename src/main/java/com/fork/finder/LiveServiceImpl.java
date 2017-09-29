package com.fork.finder;

import com.fork.calc.MatchService;
import com.fork.model.enums.BookMakersMatch;
import com.fork.model.Live;
import com.fork.parser.match.MatchParser;
import com.fork.repository.LiveRepository;
import com.fork.thread.RunLive;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Log4j
@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private LiveRepository liveRepository;
    @Autowired
    private MatchService matchService;

    private RunLive runLive;
    private List<MatchParser> parsers;

    @Override
    public List<Live> getLives() {
        return liveRepository.findAllByOrderByTimeDesc();
    }

    @Override
    public void clearLives() {
        liveRepository.deleteAll();
    }

    @Override
    public void stopLive() {
        if(nonNull(runLive))
            runLive.kill();

        if(nonNull(parsers) && !parsers.isEmpty() )
            for(MatchParser parser : parsers){
                parser.closeBrowser();
            }
    }

    @Override
    public void startLive() {
        parsers = new ArrayList();

        for(BookMakersMatch bookMaker : BookMakersMatch.values()){
            parsers.add(startSite(bookMaker.getName()));
        }

        runLive = new RunLive(liveRepository, matchService, parsers);

        Thread thread = new Thread(runLive);
        thread.start();
    }

    private MatchParser startSite(String bookMaker) {
        MatchParser parser = getParser(bookMaker);
        parser.goToSite();
        return parser;
    }

    private MatchParser getParser(String type){
        return appContext.getBean(type, MatchParser.class);
    }
}
