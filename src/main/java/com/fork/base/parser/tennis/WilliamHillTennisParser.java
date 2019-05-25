package com.fork.base.parser.tennis;

import com.fork.base.model.Bet;
import com.fork.base.model.BookMaker;
import com.fork.base.model.Match;
import com.fork.base.model.enums.BookMakers;
import com.fork.base.model.enums.ParseType;
import com.fork.base.model.enums.SportTypes;
import com.fork.base.parser.BaseParser;
import com.fork.util.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Component("WilliamHillTennis")
public class WilliamHillTennisParser extends BaseParser {

    private static final Logger log = Logger.getLogger(WilliamHillTennisParser.class);

    private final static String URL = "http://sports.williamhill.com/bet/en-gb/betting/y/17/mh/Tennis.html";
    private final static String MATCHES = "//table[contains(@class, 'tableData')]/tbody/tr";

    public WilliamHillTennisParser() {
        bookMaker = new BookMaker(BookMakers.WILLIAMHILL.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite() throws Exception{
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
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

        nextPageXpath = "//a[contains(@class, 'rn_whPaginator_last')]";
        countPages = driver.findElements(By.xpath("//span[contains(@class, 'rn_PageLinks')]/a")).size() + 1;

        Thread.sleep(1000);
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
                boolean isLive = !time.contains("EEST") && !time.contains("EET");

                if(isLive) {
                    time = "Live";
                }
                if(isLive && ParseType.WITHOUT_LIVE.isEquals(parseType)) {
                    continue;
                }
                if(!isLive && ParseType.ONLY_LIVE.isEquals(parseType)) {
                    continue;
                }

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
                match.setBookMaker(BookMakers.WILLIAMHILL.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setParsDate(LocalDateTime.now());
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
