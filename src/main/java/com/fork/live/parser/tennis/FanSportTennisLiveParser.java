package com.fork.live.parser.tennis;

import com.fork.base.model.Bet;
import com.fork.base.parser.BaseParser;
import com.fork.live.model.BookMakersLive;
import com.fork.live.model.LiveMatch;
import com.fork.live.parser.LiveParser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component("FanSportTennisLive")
public class FanSportTennisLiveParser implements LiveParser {

    private static final Logger log = Logger.getLogger(FanSportTennisLiveParser.class);

    private WebDriver driver;

    @Override
    public void goToSite(String url) throws Exception{
        log.info("Enter the site " + getBookMakerName());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        Thread.sleep(1000);

        driver.findElement(By.className("labelFdropAct")).click();
        driver.findElement(By.xpath("//div[@data-type='500']")).click();

        Thread.sleep(1000);
    }

    @Override
    public LiveMatch parsMatch() {
        log.info("Pars Match");

        LiveMatch match = new LiveMatch(getBookMakerName());

        match.setWinner(parsBet("1x2"));

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
            List<WebElement> baseElements = driver.findElements(By.xpath("//div[contains(text(), '" + name + "')]/ancestor::div"));
            List<WebElement> bets = baseElements.get(0).findElements(By.className("koeff"));

            Bet bet = new Bet();
            bet.setName(name);
            bet.setBookMaker(getBookMakerName());
            bet.setLeft(Double.parseDouble(bets.get(0).getText()));
            bet.setRight(Double.parseDouble(bets.get(1).getText()));
            return bet;
        } catch (Exception e) {
            log.error("Pars Error");
            return null;
        }
    }

    @Override
    public void closeBrowser() {
        log.info("Close browser");
        if(nonNull(driver))
            driver.close();
    }

    private String getBookMakerName(){
        return BookMakersLive.FANSPORT.getName();
    }
}
