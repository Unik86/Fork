package com.fork.controller;

import com.fork.model.Fork;
import com.fork.finder.FindForkService;
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

    private FindForkService findForkService;

    public ForkController(FindForkService findForkService) {
        this.findForkService = findForkService;
    }

    @RequestMapping(value = "/")
    public String main() {
        return "header";
    }

    @GetMapping(value = "/parseAll")
    public String parseAllButton() {
        log.info("GET /parseAll [BEGIN]");

        findForkService.findFork();

        log.info("GET /parseAll [END]");
        return "fork";
    }

    @GetMapping(value = "/parseMatch")
    public String parseMatchButton(@RequestParam("fork") Fork fork) {
        log.info("GET /parseMatch [BEGIN]");

        findForkService.findMatchFork(fork);

        log.info("GET /parseMatch [END]");
        return "fork";
    }

    @GetMapping(value = "/getForks")
    public String getWillMatches(Model model) {
        log.info("GET /getForks [BEGIN]");

        List<Fork> forks =  findForkService.getForks();
        model.addAttribute("forks", forks);

        log.info("GET /getForks [END]");
        return "fork";
    }

    @GetMapping(value = "/getTwoOfThree")
    public String getTwoOfThree(Model model) {
        log.info("GET /getTwoOfThree [BEGIN]");

        List<TwoOfThree> twoOfThrees =  findForkService.getTwoOfThrees();
        model.addAttribute("twoOfThrees", twoOfThrees);

        log.info("GET /getTwoOfThree [END]");
        return "twoOfThree";
    }

    @GetMapping(value = "/getMatches")
    public String getWillMatches(@RequestParam("type") String type, Model model) {
        log.info("GET /getMatches " + type + " [BEGIN]");

        List<Match> matches =  findForkService.getMatches(type);
        model.addAttribute("matches", matches);

        log.info("GET /getMatches " + type + " [END]");
        return "match";
    }

}
