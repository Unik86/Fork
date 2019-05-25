package com.fork.live.service;

import com.fork.base.model.Fork;
import com.fork.base.model.enums.SportTypes;
import com.fork.base.repository.ForkRepository;
import com.fork.live.model.BookMakersLive;
import com.fork.live.parser.LiveParser;
import com.fork.base.service.FindForkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.fork.util.Utils.getUrlByDomain;
import static com.fork.util.Utils.isNotEmpty;
import static java.util.Objects.nonNull;

@Service
public class LiveService {

    private static final Logger log = Logger.getLogger(LiveService.class);

    private String sportType = SportTypes.TENNIS.getType();

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private ForkRepository forkRepository;
    @Autowired
    private FindForkService findForkService;

    private LiveRunner liveRunner;
    private List<LiveParser> parsers;

    public void startLive(List<String> urls) {
        parsers = new ArrayList<>();

        for(BookMakersLive bookMaker : BookMakersLive.values()){
            String url = getUrlByDomain(urls, bookMaker.getName().toLowerCase());

            if(isNotEmpty(url)) {
                parsers.add(startSite(
                        bookMaker.getName() + sportType + "Live",
                        url
                ));
            }
        }

        liveRunner = new LiveRunner(forkRepository, findForkService, parsers);

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

    public List<Fork> getForks() {
        return forkRepository.findAllByOrderByForkBetAscParsDateAsc();
    }

    public void clearForks() {
        forkRepository.deleteAll();
    }

    private LiveParser startSite(String bookMakerName, String url) {
        LiveParser parser = getParser(bookMakerName.replaceAll("-", ""));

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
