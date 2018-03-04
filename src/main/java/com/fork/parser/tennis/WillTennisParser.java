package com.fork.parser.tennis;

import com.fork.model.Bet;
import com.fork.model.BookMaker;
import com.fork.model.Match;
import com.fork.model.enums.BookMakers;
import com.fork.model.enums.SportTypes;
import com.fork.parser.BaseParser;
import com.fork.util.Constants;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Log4j
@Component("WilliamHillTennis")
public class WillTennisParser extends BaseParser {

    private final static String URL = "http://sports.williamhill.com/bet/en-gb/betting/y/17/mh/Tennis.html";
    private final static String MATCHES = "//table[contains(@class, 'tableData')]/tbody/tr[contains(@class, 'rowOdd')]";

    public WillTennisParser() {
        pagesStr = "//span[contains(@class, 'rn_PageLinks')]/a";
        bookMaker = new BookMaker(BookMakers.WILL.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite(){
        log.info(getLog("Enter the site " + URL));

        try {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(URL);

            Select timeZone = new Select(driver.findElement(By.name("time_zone")));
            timeZone.selectByVisibleText("Europe/Kiev");
            driver.findElement(By.id("yesBtn")).click();

            Select rateFormat = new Select(driver.findElement(By.name("oddsType")));
            rateFormat.selectByVisibleText("Decimal");

            Select orderFormat = new Select(driver.findElement(By.id("changeOrder")));
            orderFormat.selectByVisibleText("Time");

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
                WebElement elementName = element.findElement(By.className("CentrePad"));
                List<WebElement> leftPad = element.findElements(By.className("leftPad"));

                String time = leftPad.get(1).getText();
                if(!time.contains("EEST"))
                    continue;

                WebElement urlElement = elementName.findElement(By.tagName("a"));
                String url = urlElement.getAttribute("href");

                if(isNull(url) || url.isEmpty())
                    url = driver.getCurrentUrl();

                String[] names = elementName.getText().split(Constants.SEPARATOR_NAME);
                if(names.length < 2)
                    continue;


                if (elementRates.size() != 2)
                    continue;

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(elementRates.get(0).getText()));
                bet.setRight(Double.parseDouble(elementRates.get(1).getText()));


                Match match = new Match();
                match.setBookMaker(BookMakers.WILL.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setUrl(url);
                match.setPlayerLeft(names[0].trim());
                match.setPlayerRight(names[1].trim());
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
