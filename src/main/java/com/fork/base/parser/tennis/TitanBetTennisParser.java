package com.fork.base.parser.tennis;

import com.fork.base.model.Bet;
import com.fork.base.model.BookMaker;
import com.fork.base.model.Match;
import com.fork.base.model.enums.BookMakers;
import com.fork.base.model.enums.SportTypes;
import com.fork.base.parser.BaseParser;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Log4j
@Component("TitanBetTennis")
public class TitanBetTennisParser extends BaseParser {

    private final static String URL = "http://sports.titanbet.com/en/tennis";
    private final static String MATCHES = "//tr[contains(@class, 'pager-item')]";
    private final static int COUNT_ROWS_ON_PAGES = 6;

    public TitanBetTennisParser() {
        bookMaker = new BookMaker(BookMakers.TITANBET.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite() throws Exception{
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        Thread.sleep(1000);

        nextPageXpath = "//button[contains(@name, 'pager-forward')]";
        String pagesStr = driver.findElement(By.xpath("//span[contains(@class, 'pager-position')]")).getText();
        countPages = Integer.parseInt(pagesStr.split(" / ")[1]);

        Thread.sleep(1000);
    }

    protected void parsOnePageMainRates(){
        int cntIds = COUNT_ROWS_ON_PAGES;
        if(countPages == pageNumber) {
            cntIds = countPages * COUNT_ROWS_ON_PAGES - driver.findElements(By.xpath(MATCHES)).size();
        }
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 1; i < cntIds; i++){
            try {
                int index = (pageNumber * cntIds + i) - COUNT_ROWS_ON_PAGES -1;
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(index);

                String time = element.findElement(By.className("time")).getText();

                String url = null;
                if(element.findElements(By.tagName("a")).size() != 0)
                    url = element.findElement(By.tagName("a")).getAttribute("href");

                if(isNull(url) || url.isEmpty())
                    url = driver.getCurrentUrl();

                List<WebElement> players = element.findElements(By.tagName("button"));

                WebElement playerLeft = players.get(0);
                WebElement playerRight = players.get(1);

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(playerLeft.findElement(By.className("dec")).getText()));
                bet.setRight(Double.parseDouble(playerRight.findElement(By.className("dec")).getText()));


                Match match = new Match();
                match.setBookMaker(BookMakers.TITANBET.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setParsDate(LocalDateTime.now());
                match.setUrl(url);
                match.setPlayerLeft(playerLeft.findElement(By.className("seln-name")).getText());
                match.setPlayerRight(playerRight.findElement(By.className("seln-name")).getText());
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
