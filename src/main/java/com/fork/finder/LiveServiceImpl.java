package com.fork.finder;

import com.fork.calc.MatchService;
import com.fork.model.enums.BookMakersMatch;
import com.fork.model.FullMatch;
import com.fork.model.Live;
import com.fork.parser.match.MatchParser;
import com.fork.repository.LiveRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j
@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private LiveRepository liveRepository;
    @Autowired
    private MatchService matchService;

    @Override
    public List<Live> getLives() {
        return liveRepository.findAllByOrderByTimeDesc();
    }

    @Override
    public void clearLives() {
        liveRepository.deleteAll();
    }

    @Override
    public void startLive() {
        Live live = new Live();
        live.setTime(new Date());
        List<FullMatch> matches = new ArrayList();

        for(BookMakersMatch bookMaker : BookMakersMatch.values()){
            matches.add(parseMatch(bookMaker.getName()));
        }

        matchService.findFork(matches);

        live.setForks(matchService.getForks());
        liveRepository.save(live);
    }

    private FullMatch parseMatch(String bookMaker) {
        MatchParser parser = getParser(bookMaker);
        parser.goToSite();
        FullMatch match = parser.parsMatch();
        parser.closeBrowser();

        return match;
    }

    private MatchParser getParser(String type){
        return appContext.getBean(type, MatchParser.class);
    }
}
