package com.fork.base.parser.tennis;

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

import static com.fork.util.Constants.SEPARATOR_NAME;

@Component("WilliamHillTennis")
public class WilliamHillTennisParser extends BaseParser {

    private static final Logger log = Logger.getLogger(WilliamHillTennisParser.class);

    private final static String URL = "https://sports.williamhill.com/betting/en-gb/tennis/matches";
    private final static String MATCHES = "//div[@class='event']";

    public WilliamHillTennisParser() {
        bookMaker = new BookMaker(BookMakers.WILLIAMHILL.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite() throws Exception {
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        WebElement oddsFormat = driver.findElement(By.className("odds-format__toggle"));
        oddsFormat.click();

        WebElement iconDecimal = driver.findElement(By.className("icon-decimal"));
        iconDecimal.click();

        Thread.sleep(1000);

        nextPageXpath = "//a[@id='filterSelectionFuture']";
        countPages = 2;

        Thread.sleep(1000);
    }

    @Override
    protected void parsOnePageMainRates() {
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for (int i = 0; i < cntIds; i++) {
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(i);

                WebElement time = element.findElement(By.tagName("time"));

                WebElement playersElement = element.findElement(By.className("btmarket__content-margin"));
                String[] players = playersElement.getText().split(SEPARATOR_NAME);

                WebElement urlElement = element.findElement(By.tagName("a"));
                String url = urlElement.getAttribute("href");

                List<WebElement> elementRates = element.findElements(By.className("btmarket__selection"));

//                if (elementRates.size() != 2)
//                    continue;

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(elementRates.get(0).getText()));
                bet.setRight(Double.parseDouble(elementRates.get(1).getText()));


                Match match = new Match();
                match.setBookMaker(BookMakers.WILLIAMHILL.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setParsDate(LocalDateTime.now());
                match.setUrl(url);
                match.setPlayerLeft(players[0]);
                match.setPlayerRight(players[1]);
                match.setTime(time.getText());

                match.setWinner(bet);
                bookMaker.getMatches().add(match);
            } catch (Exception e) {
                log.error(getLog("Pars error"));
                continue;
            }
        }
    }
}
