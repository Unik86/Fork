package com.fork.controller;

import com.fork.model.Fork;
import com.fork.finder.FindForkService;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;
import com.fork.model.enums.SportTypes;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Controller
public class ForkController {

    private FindForkService findForkService;

    public ForkController(FindForkService findForkService) {
        this.findForkService = findForkService;
    }

    @GetMapping(value = "/")
    public String main(Model model) {
        addSportType(model);
        return "main";
    }

    @GetMapping(value = "/sportType")
    public String sportType(@RequestParam("type") String type, Model model) {
        log.info("GET /sportType " + type + "[BEGIN]");

        findForkService.setSportType(type);
        addSportType(model);

        log.info("GET /sportType " + type + "[END]");
        return "main";
    }

    @GetMapping(value = "/parseAll")
    public String parseAll(Model model) {
        log.info("GET /parseAll [BEGIN]");

        findForkService.parseAll();
        addSportType(model);

        log.info("GET /parseAll [END]");
        return "fork";
    }

    @GetMapping(value = "/parseBookMaker")
    public String parseBookMaker(@RequestParam("name") String name, Model model) {
        log.info("GET /parseBookMaker " + name + "[BEGIN]");

        findForkService.parseBookMaker(name);
        addSportType(model);

        log.info("GET /parseBookMaker " + name + "[END]");
        return "fork";
    }

    @GetMapping(value = "/parseMatch")
    public String parseMatch(@RequestParam("fork") Fork fork, Model model) {
        log.info("GET /parseMatch [BEGIN]");

        findForkService.findMatchFork(fork);
        addSportType(model);

        log.info("GET /parseMatch [END]");
        return "fork";
    }

    @GetMapping(value = "/countUp")
    public String countUp(Model model) {
        log.info("GET /countUp [BEGIN]");

        findForkService.countUp();
        addSportType(model);

        log.info("GET /countUp [END]");
        return "fork";
    }

    @GetMapping(value = "/getForks")
    public String getForks(Model model) {
        log.info("GET /getForks [BEGIN]");

        List<Fork> forks =  findForkService.getForks();
        model.addAttribute("forks", forks);
        addSportType(model);

        log.info("GET /getForks [END]");
        return "fork";
    }

    @GetMapping(value = "/getTwoOfThree")
    public String getTwoOfThree(Model model) {
        log.info("GET /getTwoOfThree [BEGIN]");

        List<TwoOfThree> twoOfThrees =  findForkService.getTwoOfThrees();
        model.addAttribute("twoOfThrees", twoOfThrees);
        addSportType(model);

        log.info("GET /getTwoOfThree [END]");
        return "twoOfThree";
    }

    @GetMapping(value = "/getMatches")
    public String getMatches(@RequestParam("name") String name, Model model) {
        log.info("GET /getMatches " + name + " [BEGIN]");

        List<Match> matches =  findForkService.getMatches(name);
        model.addAttribute("matches", matches);
        addSportType(model);

        log.info("GET /getMatches " + name + " [END]");
        return "match";
    }

    @GetMapping(value = "/exportExcel")
    public ModelAndView exportExcel(HttpServletResponse response) {
        log.info("GET /exportExcel [BEGIN]");

        response.setHeader("Content-disposition", "attachment; filename=" + "forks" + ".xlsx");
        List<Fork> forks =  findForkService.getForks();

        log.info("GET /exportExcel [END]");
        return new ModelAndView("fork", "forks", forks);
    }

    private void addSportType(Model model){
        model.addAttribute("sportType", findForkService.getSportType());
    }
}
