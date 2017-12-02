package com.fork.thread;

import com.fork.model.FullMatch;
import com.fork.model.Live;
import com.fork.parser.MatchParser;
import com.fork.repository.LiveRepository;
import com.fork.service.FindForkService;
import com.fork.service.ForkResult;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j
public class RunLive implements Runnable {

    private LiveRepository liveRepository;
    private FindForkService findForkService;
    private List<MatchParser> parsers;

    private volatile boolean isRunning = true;

    public RunLive(LiveRepository liveRepository, FindForkService findForkService, List<MatchParser> parsers) {
        this.liveRepository = liveRepository;
        this.findForkService = findForkService;
        this.parsers = parsers;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(20000);
                log.info("New circle");

                List<FullMatch> matches = new ArrayList();

                for (MatchParser parser : parsers) {
                    matches.add(parser.parsMatch());
                }

                ForkResult forkResult = findForkService.findFork(matches);

                Live live = new Live();
                live.setTime(new Date());

                live.setForks(forkResult.getForks());
                liveRepository.save(live);
            } catch (Exception e){
                log.error("While failure");
            }
        }
    }

    public void kill() {
        isRunning = false;
    }
}
