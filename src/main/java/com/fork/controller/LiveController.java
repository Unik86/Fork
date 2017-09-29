package com.fork.controller;

import com.fork.finder.LiveService;
import com.fork.model.FullMatch;
import com.fork.model.Live;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j
@Controller
public class LiveController {

    private LiveService liveService;

    public LiveController(LiveService liveService) {
        this.liveService = liveService;
    }

    @RequestMapping(value = "/live")
    public String main() {
        return "live";
    }

    @GetMapping(value = "/startLive")
    public String startLive(Model model) {
        log.info("GET /startLive [BEGIN]");

        liveService.startLive();
        List<Live> lives =  liveService.getLives();
        model.addAttribute("lives", lives);

        log.info("GET /startLive [END]");
        return "live";
    }

    @GetMapping(value = "/getLives")
    public String getLives(Model model) {
        log.info("GET /getLives [BEGIN]");

        List<Live> lives =  liveService.getLives();
        model.addAttribute("lives", lives);

        log.info("GET /getLives [END]");
        return "live";
    }

    @GetMapping(value = "/clearLives")
    public String clearLives() {
        log.info("GET /clearLives [BEGIN]");

        liveService.clearLives();

        log.info("GET /clearLives [END]");
        return "live";
    }
}
