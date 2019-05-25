package com.fork.base.controller;

import com.fork.base.model.Fork;
import com.fork.base.model.ParseResult;
import com.fork.base.service.ForkService;
import com.fork.base.model.Match;
import com.fork.base.model.TwoOfThree;
import com.google.common.collect.ImmutableList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ForkController {

    private static final Logger log = Logger.getLogger(ForkController.class);

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

    @GetMapping(value = "/clearAll")
    public String clearAll() {
        log.info("GET /clearAll [BEGIN]");
        forkService.clearAll();
        log.info("GET /clearAll [END]");
        return "main";
    }

    @GetMapping(value = "/parseAll")
    public String parseAll(String group, String parseType, Model model) {
        log.info("GET /parseAll [BEGIN]");

        List<ParseResult> parseResults = forkService.parseAll(group, parseType);
        model.addAttribute("results", parseResults);
        addSportType(model);

        log.info("GET /parseAll [END]");
        return "base/parseResult";
    }

    @GetMapping(value = "/parseBookMaker")
    public String parseBookMaker(@RequestParam("name") String name, String parseType, Model model) {
        log.info("GET /parseBookMaker " + name + "[BEGIN]");

        ParseResult parseResult = forkService.parseBookMaker(name, parseType);
        model.addAttribute("results", ImmutableList.of(parseResult));
        addSportType(model);

        log.info("GET /parseBookMaker " + name + "[END]");
        return "base/parseResult";
    }

//    @GetMapping(value = "/parseMatch")
//    public String parseMatch(@RequestParam("fork") Fork fork, Model model) {
//        log.info("GET /parseMatch [BEGIN]");
//
//        forkService.findMatchFork(fork);
//        addSportType(model);
//
//        log.info("GET /parseMatch [END]");
//        return "base/fork";
//    }

    @GetMapping(value = "/countUp")
    public String countUp(Model model) {
        log.info("GET /countUp [BEGIN]");

        String result = forkService.countUp();
        model.addAttribute("result", result);
        addSportType(model);

        log.info("GET /countUp [END]");
        return "main";
    }

    @GetMapping(value = "/getForks")
    public String getForks(Model model) {
        log.info("GET /getForks [BEGIN]");

        List<Fork> forks =  forkService.getForks();
        model.addAttribute("forks", forks);
        addSportType(model);

        log.info("GET /getForks [END]");
        return "base/fork";
    }

    @GetMapping(value = "/getTwoOfThree")
    public String getTwoOfThree(Model model) {
        log.info("GET /getTwoOfThree [BEGIN]");

        List<TwoOfThree> twoOfThrees =  forkService.getTwoOfThrees();
        model.addAttribute("twoOfThrees", twoOfThrees);
        addSportType(model);

        log.info("GET /getTwoOfThree [END]");
        return "base/twoOfThree";
    }

    @GetMapping(value = "/getMatches")
    public String getMatches(@RequestParam("name") String name, Model model) {
        log.info("GET /getMatches " + name + " [BEGIN]");

        List<Match> matches =  forkService.getMatches(name);
        model.addAttribute("matches", matches);
        addSportType(model);

        log.info("GET /getMatches " + name + " [END]");
        return "base/match";
    }

    private void addSportType(Model model){
        model.addAttribute("sportType", forkService.getSportType());
    }
}
