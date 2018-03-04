package com.fork.parser.football.match;

import com.fork.model.*;
import com.fork.model.enums.BookMakers;
import com.fork.parser.MatchParser;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Log4j
@Component("WilliamHillMatchFootball")
public class WillFootballMatchParser implements MatchParser {

    private final static String URL = "http://sports.williamhill.com/bet/en-gb/betting/e/11722834/Twente+v+Heracles.html";

    private WebDriver driver;

    public void goToSite() {
        log.info("Enter the site " + getBookMakerName());

        try {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(URL);

            Select timeZone = new Select(driver.findElement(By.name("time_zone")));
            timeZone.selectByVisibleText("Europe/Kiev");
            driver.findElement(By.id("yesBtn")).click();

            Select rateFormat = new Select(driver.findElement(By.name("oddsType")));
            rateFormat.selectByVisibleText("Decimal");

            Thread.sleep(1000);
        } catch (Exception e){
            driver.close();
            log.error("Enter the site failure");
        }
    }

    @Override
    public FullMatch parsMatch() {
        log.info("Pars Match");

        FullMatch match = new FullMatch(getBookMakerName());

        match.setWinner(parsBet("Match Betting Live"));

        match.setTotal05(parsBet("Match Under/Over 0.5 Goals Live"));
        match.setTotal15(parsBet("Match Under/Over 1.5 Goals Live"));
        match.setTotal25(parsBet("Match Under/Over 2.5 Goals Live"));
        match.setTotal35(parsBet("Match Under/Over 3.5 Goals Live"));
        match.setTotal45(parsBet("Match Under/Over 4.5 Goals Live"));
        match.setTotal55(parsBet("Match Under/Over 5.5 Goals Live"));
        match.setTotal65(parsBet("Match Under/Over 6.5 Goals Live"));
        match.setTotal75(parsBet("Match Under/Over 7.5 Goals Live"));
        match.setTotal85(parsBet("Match Under/Over 8.5 Goals Live"));
        match.setTotal95(parsBet("Match Under/Over 9.5 Goals Live"));

        return match;
    }

    private Bet parsBet(String name) {
        try {
            List<WebElement> elements = driver.findElements(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::table/tbody/tr/td"));
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
        return BookMakers.WILL.getName();
    }

}
