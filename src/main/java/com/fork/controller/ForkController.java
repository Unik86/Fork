package com.fork.controller;

import com.fork.model.Fork;
import com.fork.service.ForkService;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@Controller
public class ForkController {

    private ForkService forkService;

    public ForkController(ForkService forkService) {
        this.forkService = forkService;
    }

    @GetMapping(value = "/")
    public String main(Model model) {
        addSportType(model);
        return "main";
    }

    @GetMapping(value = "/sportType")
    public String sportType(@RequestParam("type") String type, Model model) {
        log.info("GET /sportType " + type + "[BEGIN]");

        forkService.setSportType(type);
        addSportType(model);

        log.info("GET /sportType " + type + "[END]");
        return "main";
    }

    @GetMapping(value = "/parseAll")
    public String parseAll(Model model) {
        log.info("GET /parseAll [BEGIN]");

        forkService.parseAll();
        addSportType(model);

        log.info("GET /parseAll [END]");
        return "fork";
    }

    @GetMapping(value = "/parseBookMaker")
    public String parseBookMaker(@RequestParam("name") String name, Model model) {
        log.info("GET /parseBookMaker " + name + "[BEGIN]");

        forkService.parseBookMaker(name);
        addSportType(model);

        log.info("GET /parseBookMaker " + name + "[END]");
        return "fork";
    }

//    @GetMapping(value = "/parseMatch")
//    public String parseMatch(@RequestParam("fork") Fork fork, Model model) {
//        log.info("GET /parseMatch [BEGIN]");
//
//        forkService.findMatchFork(fork);
//        addSportType(model);
//
//        log.info("GET /parseMatch [END]");
//        return "fork";
//    }

    @GetMapping(value = "/countUp")
    public String countUp(Model model) {
        log.info("GET /countUp [BEGIN]");

        forkService.countUp();
        addSportType(model);

        log.info("GET /countUp [END]");
        return "fork";
    }

    @GetMapping(value = "/getForks")
    public String getForks(Model model) {
        log.info("GET /getForks [BEGIN]");

        List<Fork> forks =  forkService.getForks();
        model.addAttribute("forks", forks);
        addSportType(model);

        log.info("GET /getForks [END]");
        return "fork";
    }

    @GetMapping(value = "/getTwoOfThree")
    public String getTwoOfThree(Model model) {
        log.info("GET /getTwoOfThree [BEGIN]");

        List<TwoOfThree> twoOfThrees =  forkService.getTwoOfThrees();
        model.addAttribute("twoOfThrees", twoOfThrees);
        addSportType(model);

        log.info("GET /getTwoOfThree [END]");
        return "twoOfThree";
    }

    @GetMapping(value = "/getMatches")
    public String getMatches(@RequestParam("name") String name, Model model) {
        log.info("GET /getMatches " + name + " [BEGIN]");

        List<Match> matches =  forkService.getMatches(name);
        model.addAttribute("matches", matches);
        addSportType(model);

        log.info("GET /getMatches " + name + " [END]");
        return "match";
    }

    private void addSportType(Model model){
        model.addAttribute("sportType", forkService.getSportType());
    }
}
