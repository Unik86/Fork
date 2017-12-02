package com.fork.controller;

import com.fork.service.ForkService;
import com.fork.model.Fork;
import com.fork.model.TwoOfThree;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j
@Controller
public class ExportController {

    private ForkService findForkService;

    public ExportController(ForkService findForkService) {
        this.findForkService = findForkService;
    }

    @GetMapping(value = "/exportForksToExcel")
    public String exportForksToExcel(HttpServletResponse response, Model model) {
        log.info("GET /exportForksToExcel [BEGIN]");

        response.setHeader(
                "Content-disposition",
                "attachment; filename=" + "Forks_" + getNowDate() + ".xlsx"
        );
        List<Fork> forks =  findForkService.getForks();
        model.addAttribute("forks", forks);

        log.info("GET /exportForksToExcel [END]");
        return "exportForks";
    }

    @GetMapping(value = "/exportForksToPdf")
    public String exportForksToPdf(HttpServletResponse response, Model model) {
        log.info("GET /exportForksToPdf [BEGIN]");

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-disposition",
                "attachment; filename=" + "Forks_" + getNowDate() + ".pdf"
        );
        List<Fork> forks =  findForkService.getForks();
        model.addAttribute("forks", forks);

        log.info("GET /exportForksToPdf [END]");
        return "exportForks";
    }

    @GetMapping(value = "/exportTwoOfThreesToExcel")
    public String exportTwoOfThreesToExcel(HttpServletResponse response, Model model) {
        log.info("GET /exportTwoOfThreesToExcel [BEGIN]");

        response.setHeader(
                "Content-disposition",
                "attachment; filename=" + "TwoOfThrees_" + getNowDate() + ".xlsx"
        );
        List<TwoOfThree> twoOfThrees =  findForkService.getTwoOfThrees();
        model.addAttribute("twoOfThrees", twoOfThrees);

        log.info("GET /exportTwoOfThreesToExcel [END]");
        return "exportTwoOfThrees";
    }

    @GetMapping(value = "/exportTwoOfThreesToPdf")
    public String exportTwoOfThreesToPdf(HttpServletResponse response, Model model) {
        log.info("GET /exportTwoOfThreesToPdf [BEGIN]");

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-disposition",
                "attachment; filename=" + "TwoOfThrees_" + getNowDate() + ".pdf"
        );
        List<TwoOfThree> twoOfThrees =  findForkService.getTwoOfThrees();
        model.addAttribute("twoOfThrees", twoOfThrees);

        log.info("GET /exportTwoOfThreesToPdf [END]");
        return "exportTwoOfThrees";
    }

    private String getNowDate(){
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date());
    }

}
