package com.fork.parser.tennis;

import com.fork.model.Bet;
import com.fork.model.BookMaker;
import com.fork.model.Match;
import com.fork.model.enums.BookMakers;
import com.fork.model.enums.SportTypes;
import com.fork.parser.BaseParser;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Log4j
@Component("BWinTennis")
public class BWinTennisParser extends BaseParser {

    private final static String URL = "https://sports.bwin.com/en/sports#sportId=5";
    private final static String MATCHES = "//div[contains(@class, 'ui-widget-content-body')]/div/div/div/div/div/div/div/div/div";

    public BWinTennisParser() {
        pagesStr = "//a[contains(@href, '?page=') and not(contains(@class,'active-page-arrow'))]";
        bookMaker = new BookMaker(BookMakers.BWIN.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite(){
        log.info(getLog("Enter the site " + URL));

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(URL);

            Thread.sleep(1000);
        } catch (Exception e){
            driver.close();
            log.error(getLog("Enter the site failure"));
        }
    }

    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(i);

                String time = element.findElement(By.tagName("div")).getText();

                String url = null;
                if(element.findElements(By.tagName("a")).size() != 0)
                    url = element.findElement(By.tagName("a")).getAttribute("href");

                if(isNull(url) || url.isEmpty())
                    url = driver.getCurrentUrl();

                List<WebElement> columns = element.findElements(By.tagName("button"));

                List<WebElement>  columnLeft = columns.get(0).findElements(By.tagName("div"));
                List<WebElement>  columnRight = columns.get(1).findElements(By.tagName("div"));

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(columnLeft.get(1).getText()));
                bet.setRight(Double.parseDouble(columnRight.get(1).getText()));


                Match match = new Match();
                match.setBookMaker(BookMakers.BWIN.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setParsDate(LocalDateTime.now());
                match.setUrl(url);
                match.setPlayerLeft(columnLeft.get(0).getText());
                match.setPlayerRight(columnRight.get(0).getText());
                match.setTime(time);

                match.setWinner(bet);
                bookMaker.getMatches().add(match);
            } catch (Exception e){
                log.error(getLog("Pars error"));
                continue;
            }
        }
    }

}
