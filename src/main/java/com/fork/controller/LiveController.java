package com.fork.controller;

import com.fork.finder.LiveService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping(value = "/start")
    public String startLive(Model model) {
        log.info("GET /start [BEGIN]");

        liveService.startLive();

        log.info("GET /start [END]");
        return "live";
    }
}
