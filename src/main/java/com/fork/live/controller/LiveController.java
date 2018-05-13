package com.fork.live.controller;

import com.fork.live.model.Live;
import com.fork.live.service.LiveService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j
@Controller
public class LiveController {

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

    @GetMapping(value = "/startLive")
    public String startLive(Model model) {
        log.info("GET /startLive [BEGIN]");

        liveService.startLive();
        List<Live> lives =  liveService.getLives();
        model.addAttribute("lives", lives);

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

        List<Live> lives =  liveService.getLives();
        model.addAttribute("lives", lives);

        log.info("GET /getLives [END]");
        return "live/live";
    }

    @GetMapping(value = "/clearLives")
    public String clearLives() {
        log.info("GET /clearLives [BEGIN]");

        liveService.clearLives();

        log.info("GET /clearLives [END]");
        return "live/live";
    }

    private void addSportType(Model model){
        model.addAttribute("liveSportType", liveService.getSportType());
    }
}
