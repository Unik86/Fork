package com.fork.live.controller;

import com.fork.base.model.Fork;
import com.fork.live.service.LiveService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LiveController {

    private static final Logger log = Logger.getLogger(LiveController.class);

    private LiveService liveService;

    public LiveController(LiveService liveService) {
        this.liveService = liveService;
    }

    @RequestMapping(value = "/live")
    public String main(Model model) {
        addSportType(model);
        return "live/live";
    }

    @GetMapping(value = "/liveSportType")
    public String sportType(@RequestParam("type") String type, Model model) {
        log.info("GET /liveSportType " + type + "[BEGIN]");

        liveService.setSportType(type);
        addSportType(model);

        log.info("GET /liveSportType " + type + "[END]");
        return "live/live";
    }

    @PostMapping(value = "/startLive")
    public String startLive(
            @RequestBody List<String> urls,
            Model model
    ) {
        log.info("GET /startLive [BEGIN]");

        liveService.startLive(urls);
        List<Fork> forks =  liveService.getForks();
        model.addAttribute("forks", forks);

        log.info("GET /startLive [END]");
        return "live/live";
    }

    @GetMapping(value = "/stopLive")
    public String stopLive() {
        log.info("GET /stopLive [BEGIN]");

        liveService.stopLive();

        log.info("GET /stopLive [END]");
        return "live/live";
    }

    @GetMapping(value = "/getLives")
    public String getLives(Model model) {
        log.info("GET /getLives [BEGIN]");

        List<Fork> forks =  liveService.getForks();
        model.addAttribute("forks", forks);

        log.info("GET /getLives [END]");
        return "live/live";
    }

    @GetMapping(value = "/clearLives")
    public String clearLives() {
        log.info("GET /clearLives [BEGIN]");

        liveService.clearForks();

        log.info("GET /clearLives [END]");
        return "live/live";
    }

    private void addSportType(Model model){
        model.addAttribute("liveSportType", liveService.getSportType());
    }
}
