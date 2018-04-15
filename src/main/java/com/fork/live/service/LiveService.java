package com.fork.live.old;

import com.fork.base.model.enums.BookMakersMatch;
import com.fork.base.parser.MatchParser;
import com.fork.base.service.FindForkService;
import com.fork.live.model.Live;
import com.fork.live.repository.LiveRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Log4j
@Service
public class LiveService {

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private LiveRepository liveRepository;
    @Autowired
    private FindForkService findForkService;

    private RunLive runLive;
    private List<MatchParser> parsers;

    public List<Live> getLives() {
        return liveRepository.findAllByOrderByTimeDesc();
    }

    public void clearLives() {
        liveRepository.deleteAll();
    }

    public void stopLive() {
        if(nonNull(runLive))
            runLive.kill();

        if(nonNull(parsers) && !parsers.isEmpty() )
            for(MatchParser parser : parsers){
                parser.closeBrowser();
            }
    }

    public void startLive() {
        parsers = new ArrayList();

        for(BookMakersMatch bookMaker : BookMakersMatch.values()){
            parsers.add(startSite(bookMaker.getName()));
        }

        runLive = new RunLive(liveRepository, findForkService, parsers);

        Thread thread = new Thread(runLive);
        thread.start();
    }

    private MatchParser startSite(String bookMaker) {
        MatchParser parser = getParser(bookMaker);
//        parser.goToSite();
        return parser;
    }

    private MatchParser getParser(String type){
        return appContext.getBean(type, MatchParser.class);
    }
}
