package com.fork.parser.football;

import com.fork.model.BookMaker;
import com.fork.parser.Parser;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.util.Objects.nonNull;

@Log4j
public abstract class BaseFootballParser implements Parser {

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
        bookMaker.getMatches().clear();
        int cntPages = 0;

        if(nonNull(pagesStr))
            cntPages = driver.findElements(By.xpath(pagesStr)).size();

        cntPages += 1;
        log.info("count pages = " + cntPages);

        for(int i = 1; i <= cntPages; i++){
            try {
                if(i != 1 && nonNull(pagesStr))
                    driver.findElements(By.xpath(pagesStr)).get(i-2).click();

                Thread.sleep(3000);
                log.info("page = " + i);
                parsOnePageMainRates();
            } catch (Exception e){

            }
        }

        log.info("matchs size = " + bookMaker.getMatches().size());
    }

    protected abstract void parsOnePageMainRates();
}
