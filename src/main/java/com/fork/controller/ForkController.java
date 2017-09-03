package com.fork.controller;

import com.fork.finder.FindForkService;
import com.fork.model.Match;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
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
    public ModelAndView parseButton() {
        log.info("GET /parse [BEGIN]");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");

        findForkService.findFork();

        log.info("GET /parse [END]");
        return mav;
    }

    @RequestMapping(value = "/getWillMatches", method = RequestMethod.GET)
    public ModelAndView getWillMatches() {
        log.info("GET /getWillMatches [BEGIN]");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");

        List<Match> matches =  findForkService.getWillMatches();
        mav.addObject(matches);

        log.info("GET /getWillMatches [END]");
        return mav;
    }

}
