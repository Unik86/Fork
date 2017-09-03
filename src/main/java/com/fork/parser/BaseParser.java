package com.fork.parser;

import com.fork.model.Match;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

@Log4j
public abstract class BaseParser implements Parser{
    protected WebDriver driver;
    protected List<Match> matchs = new ArrayList<>();

    @Override
    public void closeBrowser() {
        log.info("Close browser");
        if(driver != null)
            driver.close();
    }

    @Override
    public List<Match> getMatchs() {
        log.info("matchs size = " + matchs.size());
        return matchs;
    }
}
