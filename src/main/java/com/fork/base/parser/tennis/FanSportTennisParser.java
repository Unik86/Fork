package com.fork.base.parser.tennis;

import com.fork.base.model.Bet;
import com.fork.base.model.BookMaker;
import com.fork.base.model.Match;
import com.fork.base.model.enums.BookMakers;
import com.fork.base.model.enums.ParseType;
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
@Component("FanSportTennis")
public class FanSportTennisParser extends BaseParser {

    private final static String URL = "https://fan-sport.com.ua/en/line/Tennis/";
    private final static String URL_LIVE = "https://fan-sport.com.ua/en/live/Tennis/";
    private final static String MATCHES = "//div[contains(@class, 'c-events__item_col')]";

    public FanSportTennisParser() {
//        bookMaker = new BookMaker(BookMakers.FANSPORT.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite() throws Exception{
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        if(ParseType.WITHOUT_LIVE.isEquals(parseType)) {
            driver.get(URL);
        } else if(ParseType.ONLY_LIVE.isEquals(parseType)) {
            driver.get(URL_LIVE);
        }

        Thread.sleep(1000);

        driver.findElement(By.className("labelFdropAct")).click();
        driver.findElement(By.xpath("//div[@data-type='500']")).click();

        Thread.sleep(1000);
    }

    @Override
    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(i);

                WebElement time = element.findElement(By.className("c-events__time"));
                List<WebElement> players = element.findElements(By.className("c-events__team"));
                List<WebElement> bets = element.findElements(By.className("c-bets__bet_sm"));

                WebElement urlElement = element.findElement(By.className("c-events__name"));
                String url = urlElement.getAttribute("href");

                if(isNull(url) || url.isEmpty())
                    url = driver.getCurrentUrl();

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(bets.get(0).getText()));
                bet.setRight(Double.parseDouble(bets.get(2).getText()));

                Match match = new Match();
//                match.setBookMaker(BookMakers.FANSPORT.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setParsDate(LocalDateTime.now());
                match.setUrl(url);
                match.setPlayerLeft(players.get(0).getText());
                match.setPlayerRight(players.get(1).getText());
                match.setTime(time.getText());

                match.setWinner(bet);
                bookMaker.getMatches().add(match);
            } catch (Exception e){
                log.error(getLog("Pars error"));
                continue;
            }
        }
    }
}
