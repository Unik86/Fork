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
@Component("UnibetTennis")
public class UnibetTennisParser extends BaseParser {

    private final static String URL = "https://www.unibet.com/betting#filter/tennis";
    private final static String MATCHES = "//a[contains(@class, 'KambiBC-event-item__link')]";

    public UnibetTennisParser() {
        bookMaker = new BookMaker(BookMakers.UNIBET.getName(), SportTypes.TENNIS.getType());
    }

    @Override
    public void goToSite() throws Exception{
        log.info(getLog("Enter the site " + URL));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        Thread.sleep(2000);

        List<WebElement> tabs = driver.findElements(By.className("KambiBC-collapsible-header"));
        for(WebElement element : tabs){
            if(element.findElements(By.className("KambiBC-mod-event-group-header__event-count")).size() > 0){
                element.click();
                Thread.sleep(1000);
            }
        }

        Thread.sleep(1000);
    }

    @Override
    protected void parsOnePageMainRates(){
        int cntIds = driver.findElements(By.xpath(MATCHES)).size();
        log.info(getLog("matches on page = " + cntIds));

        for(int i = 0; i < cntIds; i++){
            try {
                WebElement element = driver.findElements(By.xpath(MATCHES)).get(i);

                List<WebElement> times = element.findElements(By.className("KambiBC-event-item__start-time--time"));
                List<WebElement> names = element.findElements(By.className("KambiBC-event-participants__name"));
                List<WebElement> rates = element.findElements(By.className("KambiBC-mod-outcome__odds"));

                if (times.isEmpty()) {
                    continue;
                }

                if (names.size() != 2) {
                    continue;
                }

                String url = element.getAttribute("href");

                if(isNull(url) || url.isEmpty()) {
                    url = driver.getCurrentUrl();
                }


                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(rates.get(0).getText()));
                bet.setRight(Double.parseDouble(rates.get(1).getText()));


                Match match = new Match();
                match.setBookMaker(BookMakers.UNIBET.getName());
                match.setSportType(SportTypes.TENNIS.getType());
                match.setParsDate(LocalDateTime.now());
                match.setUrl(url);
                match.setPlayerLeft(names.get(0).getText());
                match.setPlayerRight(names.get(1).getText());
                match.setTime(times.get(0).getText());

                match.setWinner(bet);
                bookMaker.getMatches().add(match);
            } catch (Exception e){
                log.error(getLog("Pars error"));
                continue;
            }
        }
    }
}
