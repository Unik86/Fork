package com.fork.controller;

import com.fork.finder.FindForkService;
import com.fork.model.Match;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j
@Controller
public class ForkController {

    private FindForkService findForkService;

    public ForkController(FindForkService findForkService) {
        this.findForkService = findForkService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/parse", method = RequestMethod.GET)
    public String parseButton() {
        log.info("GET /parse [BEGIN]");

        findForkService.findFork();

        log.info("GET /parse [END]");
        return "main";
    }

    @RequestMapping(value = "/getMatches", method = RequestMethod.GET)
    public String getWillMatches(@RequestParam("type") String type, Model model) {
        log.info("GET /getMatches " + type + " [BEGIN]");

        List<Match> matches =  findForkService.getMatches(type);
        model.addAttribute("matches", matches);

        log.info("GET /getMatches " + type + " [END]");
        return "main";
    }

}
