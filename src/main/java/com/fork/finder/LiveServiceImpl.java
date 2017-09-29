package com.fork.finder;

import com.fork.model.BookMakersMatch;
import com.fork.parser.MatchParser;
import com.fork.repository.MatchRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public void startLive() {
        for(BookMakersMatch bookMaker : BookMakersMatch.values()){
            parseMatch(bookMaker.getName());
        }
    }

    private void parseMatch(String bookMaker) {
        MatchParser parser = getParser(bookMaker);
        parser.goToSite();
        matchRepository.save(parser.parsMatch());
        parser.closeBrowser();
    }

    private MatchParser getParser(String type){
        return appContext.getBean(type, MatchParser.class);
    }
}
