package com.fork.live.service;

import com.fork.base.model.enums.SportTypes;
import com.fork.live.model.BookMakersLive;
import com.fork.live.parser.LiveParser;
import com.fork.base.service.FindForkService;
import com.fork.live.model.Live;
import com.fork.live.repository.LiveRepository;
import lombok.Getter;
import lombok.Setter;
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

    @Getter
    @Setter
    private String sportType = SportTypes.TENNIS.getType();

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private LiveRepository liveRepository;
    @Autowired
    private FindForkService findForkService;

    private LiveRunner liveRunner;
    private List<LiveParser> parsers;

    public void startLive() {
        parsers = new ArrayList<>();

        for(BookMakersLive bookMaker : BookMakersLive.values()){
            parsers.add(startSite(
                    bookMaker.getName() + sportType,
                    null
            ));
        }

        liveRunner = new LiveRunner(liveRepository, findForkService, parsers);

        Thread thread = new Thread(liveRunner);
        thread.start();
    }

    public void stopLive() {
        if(nonNull(liveRunner))
            liveRunner.kill();

        if(nonNull(parsers) && !parsers.isEmpty() )
            for(LiveParser parser : parsers){
                parser.closeBrowser();
            }
    }

    public List<Live> getLives() {
        return liveRepository.findAllByOrderByTimeDesc();
    }

    public void clearLives() {
        liveRepository.deleteAll();
    }

    private LiveParser startSite(String bookMakerName, String url) {
        LiveParser parser = getParser(bookMakerName);

        try {
            parser.goToSite(url);
        } catch (Exception e) {
            log.error("Error parser >>>>>>>>>> " + bookMakerName);
        }

        return parser;
    }

    private LiveParser getParser(String type){
        return appContext.getBean(type, LiveParser.class);
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }
}
