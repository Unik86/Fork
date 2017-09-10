package com.fork.parser;

import com.fork.model.Match;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

@Log4j
public abstract class BaseParser implements Parser{

    protected WebDriver driver;
    protected List<Match> matchs = new ArrayList<>();

    protected String pagesStr;

    @Override
    public void closeBrowser() {
        log.info("Close browser");
        if(driver != null)
            driver.close();
    }

    @Override
    public List<Match> getMatchs() {
        return matchs;
    }

    @Override
    public List<Match> parsMainRates(){
        log.info("Pars main rates");
        matchs.clear();

        int cntPages = driver.findElements(By.xpath(pagesStr)).size();
        log.info("count pages = " + cntPages);

        log.info("page = " + 1);
        parsOnePageMainRates();

        for(int i = 0; i < cntPages; i++){
            try {
                driver.findElements(By.xpath(pagesStr)).get(i).click();
                log.info("page = " + (i + 2));
                Thread.sleep(3000);
                parsOnePageMainRates();
            } catch (Exception e){

            }
        }

        log.info("matchs size = " + matchs.size());
        return matchs;
    }

    protected abstract void parsOnePageMainRates();
}
