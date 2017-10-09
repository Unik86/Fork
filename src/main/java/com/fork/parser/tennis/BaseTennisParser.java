package com.fork.parser.tennis;

import com.fork.model.BookMaker;
import com.fork.parser.Parser;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Log4j
public abstract class BaseTennisParser implements Parser {

    protected WebDriver driver;
    protected BookMaker bookMaker;

    protected String pagesStr;

    @Override
    public void closeBrowser() {
        log.info("Close browser");
        if(nonNull(driver))
            driver.close();
    }

    @Override
    public BookMaker getBookMaker() {
        return bookMaker;
    }

    @Override
    public void parsMainRates(){
        log.info("Pars main rates");

        parsOnePageMainRates();

        log.info("matchs size = " + bookMaker.getMatches().size());
    }

    private int getRandomInt(){
        Random rand = new Random();
        return rand.nextInt(7000) + 2000;
    }

    protected abstract void parsOnePageMainRates();
}
