package com.fork.live.service;

import com.fork.base.model.enums.BookMakersMatch;
import com.fork.live.parser.LiveParser;
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

    private LiveRunner liveRunner;
    private List<LiveParser> parsers;

    public List<Live> getLives() {
        return liveRepository.findAllByOrderByTimeDesc();
    }

    public void clearLives() {
        liveRepository.deleteAll();
    }

    public void stopLive() {
        if(nonNull(liveRunner))
            liveRunner.kill();

        if(nonNull(parsers) && !parsers.isEmpty() )
            for(LiveParser parser : parsers){
                parser.closeBrowser();
            }
    }

    public void startLive() {
        parsers = new ArrayList();

        for(BookMakersMatch bookMaker : BookMakersMatch.values()){
            parsers.add(startSite(bookMaker.getName()));
        }

        liveRunner = new LiveRunner(liveRepository, findForkService, parsers);

        Thread thread = new Thread(liveRunner);
        thread.start();
    }

    private LiveParser startSite(String bookMaker) {
        LiveParser parser = getParser(bookMaker);
//        parser.goToSite();
        return parser;
    }

    private LiveParser getParser(String type){
        return appContext.getBean(type, LiveParser.class);
    }
}
