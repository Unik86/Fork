package com.fork.controller;

import com.fork.finder.FindForkService;
import com.fork.model.Fork;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j
@Controller
public class ExportController {

    private FindForkService findForkService;

    public ExportController(FindForkService findForkService) {
        this.findForkService = findForkService;
    }

    @GetMapping(value = "/exportExcel")
    public String exportExcel(HttpServletResponse response, Model model) {
        log.info("GET /exportExcel [BEGIN]");

        response.setHeader(
                "Content-disposition",
                "attachment; filename=" + "Forks_" + getNowDate() + ".xlsx"
        );
        List<Fork> forks =  findForkService.getForks();
        model.addAttribute("forks", forks);

        log.info("GET /exportExcel [END]");
        return "export";
    }

    @GetMapping(value = "/exportPdf")
    public String exportPdf(HttpServletResponse response, Model model) {
        log.info("GET /exportPdf [BEGIN]");

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-disposition",
                "attachment; filename=" + "\"Forks_" + getNowDate() + ".pdf\""
        );
        List<Fork> forks =  findForkService.getForks();
        model.addAttribute("forks", forks);

        log.info("GET /exportPdf [END]");
        return "export";
    }

    private String getNowDate(){
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date());
    }

}
