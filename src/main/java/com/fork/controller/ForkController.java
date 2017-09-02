package com.fork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForkController {

    private int cnt = 0;

    @RequestMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView start() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");

        String str = "Hello World!";
        mav.addObject("message", str);

        return mav;
    }

    @RequestMapping(value = "/get-data", method = RequestMethod.GET)
    public ModelAndView btn() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");

        String str = "Hello " + cnt++;
        mav.addObject("message", str);

        return mav;
    }

}
