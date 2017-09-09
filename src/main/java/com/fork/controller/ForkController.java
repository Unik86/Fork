package com.fork.controller;

import com.fork.finder.FindForkService;
import com.fork.model.Match;
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
        return "main";
    }

    @GetMapping(value = "/parse")
    public String parseButton() {
        log.info("GET /parse [BEGIN]");

        findForkService.findFork();

        log.info("GET /parse [END]");
        return "main";
    }

    @GetMapping(value = "/getMatches")
    public String getWillMatches(@RequestParam("type") String type, Model model) {
        log.info("GET /getMatches " + type + " [BEGIN]");

        List<Match> matches =  findForkService.getMatches(type);
        model.addAttribute("matches", matches);

        log.info("GET /getMatches " + type + " [END]");
        return "main";
    }

}
