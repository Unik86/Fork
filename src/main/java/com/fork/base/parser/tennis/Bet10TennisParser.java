package com.fork.base.parser.tennis;

import com.fork.base.model.Bet;
import com.fork.base.model.BookMaker;
import com.fork.base.model.Match;
import com.fork.base.model.enums.BookMakers;
import com.fork.base.model.enums.SportTypes;
import com.fork.base.parser.BaseParser;
import com.fork.util.Constants;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.fork.util.Utils.randomInt;
import static com.fork.util.Utils.reverseWords;
import static java.util.Objects.isNull;

@Log4j
@Component("Bet10Tennis")
public class Bet10TennisParser extends BaseParser {

    private final static String URL = "https://www.10bet.com/sports/";
    private final static String MATCHES = "//div[contains(@class, 'event-upcoming')]";

    public Bet10TennisParser() {
        bookMaker = new BookMaker(BookMakers.BET10.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite() throws Exception{
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@href='/resp-todays-events/']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='Center_TodaysEventsResponsiveBlock_49846sport_6']")).click();
        Thread.sleep(1000);

        List<WebElement> tabs = driver.findElements(
                By.xpath("//h3[contains(@id, 'league-heading_') and not(contains(@class, 'expanded'))]")
        );
        for(WebElement element : tabs){
            try {
                element.click();
                Thread.sleep(randomInt(500, 2000));
            } catch (Exception e) {
                log.error("Expand Error : " + e.getMessage());
            }
        }
    }

    @Override
    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(i);

                String time = element.findElement(By.className("event-details-side-right")).getText();

                String playerLeft = element.findElement(By.className("event-details-team-a")).getText();
                String playerRight = element.findElement(By.className("event-details-team-b")).getText();

                String url = null;
                if(element.findElements(By.tagName("a")).size() != 0)
                    url = element.findElement(By.tagName("a")).getAttribute("href");

                if(isNull(url) || url.isEmpty())
                    url = driver.getCurrentUrl();

                List<WebElement> bets = element.findElements(By.className("bet-odds-number"));

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(bets.get(0).getText()));
                bet.setRight(Double.parseDouble(bets.get(1).getText()));


                Match match = new Match();
                match.setBookMaker(BookMakers.BET10.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setParsDate(LocalDateTime.now());
                match.setUrl(url);
                match.setPlayerLeft(playerLeft);
                match.setPlayerRight(playerRight);
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
