package com.fork.parser.football;

import com.fork.model.Bet;
import com.fork.model.BookMaker;
import com.fork.model.Match;
import com.fork.model.enums.BookMakers;
import com.fork.model.enums.SportTypes;
import com.fork.parser.BaseParser;
import com.fork.util.Constants;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import java.util.List;

@Log4j
@Component("WilliamHillFootball")
public class WillFootballParser extends BaseParser {

    private final static String URL = "http://sports.williamhill.com/bet/en-gb/betting/y/5/tm/0/Football.html";
    private final static String MATCHES = "//table[contains(@class, 'tableData')]/tbody/tr[contains(@class, 'rowOdd')]";

    public WillFootballParser() {
        pagesStr = "//span[contains(@class, 'rn_PageLinks')]/a";
        bookMaker = new BookMaker(BookMakers.WILL.getName(), SportTypes.FOOTBALL.getType());
    }

    @Override
    public void goToSite(){
        log.info(getLog("Enter the site " + URL));

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(URL);

            Select timeZone = new Select(driver.findElement(By.name("time_zone")));
            timeZone.selectByVisibleText("Europe/Kiev");
            driver.findElement(By.id("yesBtn")).click();

            Select rateFormat = new Select(driver.findElement(By.name("oddsType")));
            rateFormat.selectByVisibleText("Decimal");

            Select changeOrder = new Select(driver.findElement(By.id("changeOrder")));
            changeOrder.selectByVisibleText("Time");

            Thread.sleep(1000);
        } catch (Exception e){
            driver.close();
            log.error(getLog("Enter the site failure"));
        }
    }

    @Override
    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(i);

                List<WebElement> elementRates = element.findElements(By.className("eventprice"));
                List<WebElement> tdCols = element.findElements(By.tagName("td"));

                String time = tdCols.get(1).getText();
                if(!time.contains("EEST"))
                    continue;

                WebElement urlElement = tdCols.get(2).findElement(By.tagName("a"));
                String url = urlElement.getAttribute("href");

                Bet bet = null;

                String[] names = tdCols.get(2).getText().split(Constants.SEPARATOR_NAME);
                if(names.length < 2)
                    continue;


                if (elementRates.size() == 3) {
                    bet = new Bet();
                    bet.setLeft(Double.parseDouble(elementRates.get(0).getText()));
                    bet.setCenter(Double.parseDouble(elementRates.get(1).getText()));
                    bet.setRight(Double.parseDouble(elementRates.get(2).getText()));
                } else if (elementRates.size() == 2) {
                    bet = new Bet();
                    bet.setLeft(Double.parseDouble(elementRates.get(0).getText()));
                    bet.setRight(Double.parseDouble(elementRates.get(1).getText()));
                } else
                    continue;


                Match match = new Match();
                match.setBookMaker(BookMakers.WILL.getName());
                match.setSportType(SportTypes.FOOTBALL.getType());
                match.setPlayerLeft(names[0].trim());
                match.setPlayerRight(names[1].trim());
                match.setTime(time);
                match.setUrl(url);

                match.setWinner(bet);
                bookMaker.getMatches().add(match);
            } catch (Exception e){
                log.error(getLog("Pars error"));
                continue;
            }
        }
    }
}
