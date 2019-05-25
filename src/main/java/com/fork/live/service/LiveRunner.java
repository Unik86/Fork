package com.fork.live.service;

import com.fork.base.model.ForkResult;
import com.fork.base.repository.ForkRepository;
import com.fork.base.service.FindForkService;
import com.fork.live.model.LiveMatch;
import com.fork.live.parser.LiveParser;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.fork.util.Utils.randomInt;

public class LiveRunner implements Runnable {

    private static final Logger log = Logger.getLogger(LiveRunner.class);

    private ForkRepository forkRepository;
    private FindForkService findForkService;
    private List<LiveParser> parsers;

    private volatile boolean isRunning = true;

    public LiveRunner(
            ForkRepository forkRepository,
            FindForkService findForkService,
            List<LiveParser> parsers
    ) {
        this.forkRepository = forkRepository;
        this.findForkService = findForkService;
        this.parsers = parsers;
    }

    @Override
    public void run() {
        forkRepository.deleteAll();

        while (isRunning) {
            try {
                Thread.sleep(randomInt(20_000, 40_000));
                log.info("New circle");

                List<LiveMatch> matches = new ArrayList<>();

                for (LiveParser parser : parsers) {
                    matches.add(parser.parsMatch());
                }

                ForkResult forkResult = findForkService.findFork(matches);

                forkRepository.save(forkResult.getForks());
            } catch (Exception e){
                log.error("While failure");
            }
        }
    }

    public void kill() {
        isRunning = false;
    }
}
