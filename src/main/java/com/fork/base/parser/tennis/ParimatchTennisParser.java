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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.fork.util.Utils.reverseWords;

@Log4j
@Component("ParimatchTennis")
public class ParimatchTennisParser extends BaseParser {

    private final static String URL = "https://www.parimatch.com/en/";
    private final static String MATCHES = "//tbody[contains(@class, 'row1 processed')]";

    public ParimatchTennisParser() {
        bookMaker = new BookMaker(BookMakers.PARIMATCH.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite() throws Exception{
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[contains(@href, '#Tennis')]/em")).click();
        driver.findElement(By.xpath("//button[text()='Show']")).click();

        Thread.sleep(1000);
    }

    @Override
    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES + "/tr")).get(i);
                List<WebElement> tds = element.findElements(By.tagName("td"));

                WebElement names = tds.get(2);
                String[] namesStr = names.getText().split(Constants.SEPARATOR_NAME_3);

                Bet bet = new Bet();

                switch(tds.size()) {
                    case 17:
                        bet.setLeft(Double.parseDouble(tds.get(8).getText()));
                        bet.setRight(Double.parseDouble(tds.get(9).getText()));
                        break;
                    case 12:
                        bet.setLeft(Double.parseDouble(tds.get(5).getText()));
                        bet.setRight(Double.parseDouble(tds.get(6).getText()));
                        break;
                    default:
                        bet.setLeft(Double.parseDouble(tds.get(3).getText()));
                        bet.setRight(Double.parseDouble(tds.get(4).getText()));
                }

                Match match = new Match();
                match.setBookMaker(BookMakers.PARIMATCH.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setParsDate(LocalDateTime.now());
                match.setUrl(driver.getCurrentUrl());
                match.setPlayerLeft(reverseWords(namesStr[0]));
                match.setPlayerRight(reverseWords(namesStr[1]));
                match.setTime(tds.get(1).getText());

                match.setWinner(bet);
                bookMaker.getMatches().add(match);
            } catch (Exception e){
                log.error(getLog("Pars error"));
                continue;
            }
        }
    }
}
