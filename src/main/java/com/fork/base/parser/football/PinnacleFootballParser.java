package com.fork.base.parser.football;

import com.fork.base.model.Bet;
import com.fork.base.model.BookMaker;
import com.fork.base.model.enums.BookMakers;
import com.fork.base.model.Match;
import com.fork.base.model.enums.SportTypes;
import com.fork.base.parser.BaseParser;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Log4j
@Component("PinnacleFootball")
public class PinnacleFootballParser extends BaseParser {

    private final static String URL = "https://www.pinnacle.com/en/odds/today/soccer";
    private final static String MATCHES = "//table[contains(@class, 'odds-data') and not(contains(@class, 'ng-scope'))]/tbody";

    public PinnacleFootballParser() {
        bookMaker = new BookMaker(BookMakers.PINNACLE.getName(), SportTypes.FOOTBALL.getType());
    }

    @Override
    public void goToSite() throws Exception{
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        Thread.sleep(1000);
    }

    protected void parsOnePageMainRates(String parseType){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 0; i < cntIds; i++){
            try {

                WebElement base = driver.findElements(By.xpath(MATCHES)).get(i);
                List<WebElement> rows = base.findElements(By.tagName("tr"));

                if(rows.size() < 3)
                    continue;

                WebElement left = rows.get(0);
                WebElement right = rows.get(1);
                WebElement center = rows.get(2);

                WebElement time = left.findElement(By.className("game-time"));

//                if(time.findElements(By.tagName("img")).size() != 0)
//                    continue;

                String url = driver.getCurrentUrl();

                String leftName = left.findElement(By.className("game-name")).getText();
                String rightName = right.findElement(By.className("game-name")).getText();

                String leftRate = left.findElement(By.className("game-moneyline")).getText();
                String rightRate = right.findElement(By.className("game-moneyline")).getText();
                String centerRate = center.findElement(By.className("game-moneyline")).getText();

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(leftRate));
                bet.setCenter(Double.parseDouble(centerRate));
                bet.setRight(Double.parseDouble(rightRate));


                Match match = new Match();
                match.setBookMaker(BookMakers.PINNACLE.getName());
                match.setSportType(SportTypes.FOOTBALL.getType());
                match.setParsDate(LocalDateTime.now());
                match.setUrl(url);
                match.setPlayerLeft(leftName);
                match.setPlayerRight(rightName);
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
