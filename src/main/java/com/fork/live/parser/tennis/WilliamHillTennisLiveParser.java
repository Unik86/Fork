package com.fork.live.parser.tennis;

import com.fork.base.model.Bet;
import com.fork.live.model.BookMakersLive;
import com.fork.live.model.LiveMatch;
import com.fork.live.parser.LiveParser;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Log4j
@Component("WilliamHillTennisLive")
public class WilliamHillTennisLiveParser implements LiveParser {

    public static final String MATCH_BETTING_LIVE = "Match Betting Live";
    private WebDriver driver;

    @Override
    public void goToSite(String url) throws Exception{
        log.info("Enter the site " + getBookMakerName());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        Select timeZone = new Select(driver.findElement(By.name("time_zone")));
        timeZone.selectByVisibleText("Europe/Kiev");
        driver.findElement(By.id("yesBtn")).click();

        Thread.sleep(1000);

        Select rateFormat = new Select(driver.findElement(By.name("oddsType")));
        rateFormat.selectByVisibleText("Decimal");

        Thread.sleep(1000);
    }

    @Override
    public LiveMatch parsMatch() {
        log.info("Pars Match");

        LiveMatch match = new LiveMatch(getBookMakerName());

        match.setWinner(parsBet("Match Betting Live"));

//        match.setSet1Game1(parsBet("1nd Set - Game 1"));
//        match.setSet1Game2(parsBet("1nd Set - Game 2"));
//        match.setSet1Game3(parsBet("1nd Set - Game 3"));
//        match.setSet1Game4(parsBet("1nd Set - Game 4"));
//        match.setSet1Game5(parsBet("1nd Set - Game 5"));
//        match.setSet1Game6(parsBet("1nd Set - Game 6"));
//        match.setSet1Game7(parsBet("1nd Set - Game 7"));
//        match.setSet1Game8(parsBet("1nd Set - Game 8"));
//        match.setSet1Game9(parsBet("1nd Set - Game 9"));
//        match.setSet1Game10(parsBet("1nd Set - Game 10"));
//        match.setSet1Game11(parsBet("1nd Set - Game 11"));
//        match.setSet1Game12(parsBet("1nd Set - Game 12"));
//
//        match.setSet2Game1(parsBet("2nd Set - Game 1"));
//        match.setSet2Game2(parsBet("2nd Set - Game 2"));
//        match.setSet2Game3(parsBet("2nd Set - Game 3"));
//        match.setSet2Game4(parsBet("2nd Set - Game 4"));
//        match.setSet2Game5(parsBet("2nd Set - Game 5"));
//        match.setSet2Game6(parsBet("2nd Set - Game 6"));
//        match.setSet2Game7(parsBet("2nd Set - Game 7"));
//        match.setSet2Game8(parsBet("2nd Set - Game 8"));
//        match.setSet2Game9(parsBet("2nd Set - Game 9"));
//        match.setSet2Game10(parsBet("2nd Set - Game 10"));
//        match.setSet2Game11(parsBet("2nd Set - Game 11"));
//        match.setSet2Game12(parsBet("2nd Set - Game 12"));
//
//        match.setSet3Game1(parsBet("3nd Set - Game 1"));
//        match.setSet3Game2(parsBet("3nd Set - Game 2"));
//        match.setSet3Game3(parsBet("3nd Set - Game 3"));
//        match.setSet3Game4(parsBet("3nd Set - Game 4"));
//        match.setSet3Game5(parsBet("3nd Set - Game 5"));
//        match.setSet3Game6(parsBet("3nd Set - Game 6"));
//        match.setSet3Game7(parsBet("3nd Set - Game 7"));
//        match.setSet3Game8(parsBet("3nd Set - Game 8"));
//        match.setSet3Game9(parsBet("3nd Set - Game 9"));
//        match.setSet3Game10(parsBet("3nd Set - Game 10"));
//        match.setSet3Game11(parsBet("3nd Set - Game 11"));
//        match.setSet3Game12(parsBet("3nd Set - Game 12"));

        return match;
    }

    private Bet parsBet(String name) {
        try {
            List<WebElement> elements = driver.findElements(By.xpath("//span[text()='" + name + "']/ancestor::table/tbody/tr/td"));
            Bet bet = new Bet();
            bet.setName(name);
            bet.setBookMaker(getBookMakerName());

            switch (elements.size()) {
                case 2:
                    bet.setLeft(Double.parseDouble(getBetRate(elements.get(0))));
                    bet.setRight(Double.parseDouble(getBetRate(elements.get(1))));
                    break;
                case 3:
                    bet.setLeft(Double.parseDouble(getBetRate(elements.get(0))));
                    bet.setCenter(Double.parseDouble(getBetRate(elements.get(1))));
                    bet.setRight(Double.parseDouble(getBetRate(elements.get(2))));
                    break;
                default:
                    bet = null;
            }
            return bet;
        } catch (Exception e) {
            log.error("Pars Error");
            return null;
        }
    }

    private String getBetRate(WebElement element){
        return element.findElement(By.className("eventprice")).getText();
    }

    @Override
    public void closeBrowser() {
        log.info("Close browser");
        if(nonNull(driver))
            driver.close();
    }

    private String getBookMakerName(){
        return BookMakersLive.WILLIAMHILL.getName();
    }
}
