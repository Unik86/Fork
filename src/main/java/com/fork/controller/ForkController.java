package com.fork.controller;

import com.fork.finder.FindForkService;
import com.fork.model.Match;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/getWillMatches", method = RequestMethod.GET)
    public String getWillMatches(Model model) {
        log.info("GET /getWillMatches [BEGIN]");

        List<Match> matches =  findForkService.getWillMatches();
        model.addAttribute("matches", matches);

        log.info("GET /getWillMatches [END]");
        return "main";
    }

}
