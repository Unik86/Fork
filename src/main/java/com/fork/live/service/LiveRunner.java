package com.fork.live.service;

import com.fork.live.parser.LiveParser;
import com.fork.base.service.FindForkService;
import com.fork.base.model.ForkResult;
import com.fork.live.model.LiveMatch;
import com.fork.live.model.Live;
import com.fork.live.repository.LiveRepository;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j
public class LiveRunner implements Runnable {

    private LiveRepository liveRepository;
    private FindForkService findForkService;
    private List<LiveParser> parsers;

    private volatile boolean isRunning = true;

    public LiveRunner(LiveRepository liveRepository, FindForkService findForkService, List<LiveParser> parsers) {
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

                List<LiveMatch> matches = new ArrayList();

                for (LiveParser parser : parsers) {
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
