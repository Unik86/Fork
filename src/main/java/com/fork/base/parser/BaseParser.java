package com.fork.base.parser;

import com.fork.base.model.BookMaker;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.fork.util.Utils.randomInt;
import static java.util.Objects.nonNull;

@Log4j
public abstract class BaseParser implements Parser {

    protected WebDriver driver;
    protected BookMaker bookMaker;
    protected String parseType;

    protected String nextPageXpath;
    protected int countPages = 1;
    protected int pageNumber;

    @Override
    public void closeBrowser() {
        log.info(getLog("Close browser"));
        if(nonNull(driver))
            driver.close();
    }

    @Override
    public BookMaker getBookMaker() {
        return bookMaker;
    }

    @Override
    public void parsMainRates(){
        log.info(getLog("Pars main rates"));
        bookMaker.getMatches().clear();

        log.info(getLog("count pages = " + countPages));

        for(int i = 1; i <= countPages; i++){
            try {
                pageNumber = i;

                if(i != 1 && nonNull(nextPageXpath)) {
                    driver.findElement(By.xpath(nextPageXpath)).click();
                }

                Thread.sleep(randomInt(3000, 4000));
                log.info(getLog("page = " + i));

                parsOnePageMainRates();
            } catch (Exception e){
                log.error("Page error : " + e);
            }
        }

        log.info(getLog("matches size = " + bookMaker.getMatches().size()));
    }

    protected String getLog(String message){
        return "{" + bookMaker.getName() + "} - " + message;
    }

    protected abstract void parsOnePageMainRates();

    @Override
    public void setParseType(String parseType) {
        this.parseType = parseType;
    }
}
