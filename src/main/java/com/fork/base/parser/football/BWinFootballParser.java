package com.fork.base.parser.football;

import com.fork.base.model.Bet;
import com.fork.base.model.BookMaker;
import com.fork.base.model.Match;
import com.fork.base.model.enums.BookMakers;
import com.fork.base.model.enums.SportTypes;
import com.fork.base.parser.BaseParser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Component("BWinFootball")
public class BWinFootballParser extends BaseParser {

    private static final Logger log = Logger.getLogger(BWinFootballParser.class);

    private final static String URL = "https://sports.bwin.com/en/sports#sportId=4";
    private final static String MATCHES = "//div[contains(@class, 'ui-widget-content-body')]/div/div/div/div/div/div/div/div/div";

    public BWinFootballParser() {
        bookMaker = new BookMaker(BookMakers.BWIN.getName(), SportTypes.FOOTBALL.getType());
    }

    @Override
    public void goToSite() throws Exception{
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        driver.findElement(By.xpath("//li[contains(@title, 'Today')]/a")).click();

        Thread.sleep(1000);

        nextPageXpath = "//a[contains(@href, '?page=') and contains(@rel,'next') and contains(@class, 'active-page-arrow')]";
        countPages = driver.findElements(
                By.xpath("//a[contains(@href, '?page=') and not(contains(@class,'active-page-arrow'))]")
        ).size() + 1;

        Thread.sleep(1000);
    }

    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(i);

                String time = element.findElement(By.tagName("div")).getText();
                String url = element.findElement(By.tagName("a")).getAttribute("href");

                if(isNull(url) || url.isEmpty())
                    url = driver.getCurrentUrl();

                List<WebElement> columns = element.findElements(By.tagName("button"));

                List<WebElement>  columnLeft = columns.get(0).findElements(By.tagName("div"));
                List<WebElement>  columnCenter = columns.get(1).findElements(By.tagName("div"));
                List<WebElement>  columnRight = columns.get(2).findElements(By.tagName("div"));

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(columnLeft.get(1).getText()));
                bet.setCenter(Double.parseDouble(columnCenter.get(1).getText()));
                bet.setRight(Double.parseDouble(columnRight.get(1).getText()));


                Match match = new Match();
                match.setBookMaker(BookMakers.BWIN.getName());
                match.setSportType(SportTypes.FOOTBALL.getType());
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
