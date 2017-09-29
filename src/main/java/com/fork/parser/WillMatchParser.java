package com.fork.parser;

import com.fork.model.*;
import com.fork.util.Constants;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Log4j
@Component("WilliamHillMatch")
public class WillMatchParser implements MatchParser{

    private final static String URL = "http://sports.williamhill.com/bet/en-gb/betting/y/5/tm/0/Football.html";

    private WebDriver driver;

    public void goToSite() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Override
    public FullMatch parsMatch() {
        FullMatch match = new FullMatch(BookMakers.WILL.getName());

        match.setWinner(parsBet("90 Minutes"));

        match.setTotal05(parsBet("Total Match Goals Over/Under 0.5 Goals"));
        match.setTotal15(parsBet("Total Match Goals Over/Under 1.5 Goals"));
        match.setTotal25(parsBet("Total Match Goals Over/Under 2.5 Goals"));
        match.setTotal35(parsBet("Total Match Goals Over/Under 3.5 Goals"));
        match.setTotal45(parsBet("Total Match Goals Over/Under 4.5 Goals"));
        match.setTotal55(parsBet("Total Match Goals Over/Under 5.5 Goals"));
        match.setTotal65(parsBet("Total Match Goals Over/Under 6.5 Goals"));
        match.setTotal75(parsBet("Total Match Goals Over/Under 7.5 Goals"));
        match.setTotal85(parsBet("Total Match Goals Over/Under 8.5 Goals"));
        match.setTotal95(parsBet("Total Match Goals Over/Under 9.5 Goals"));

        return match;
    }

    private Bet parsBet(String name) {
        try {
            WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'"
                    + name + "')]/ancestor::table/tbody/tr/td[1]/div/div[1]"));
            WebElement element2 = driver.findElement(By.xpath("//span[contains(text(),'"
                    + name + "')]/ancestor::table/tbody/tr/td[2]/div/div[1]"));

            try {
                WebElement element3 = driver.findElement(By.xpath("//span[contains(text(),'"
                        + name + "')]/ancestor::table/tbody/tr/td[3]/div/div[1]"));

                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(element1.getText()));
                bet.setCenter(Double.parseDouble(element2.getText()));
                bet.setRight(Double.parseDouble(element3.getText()));
                return bet;
            } catch (NoSuchElementException e) {
                Bet bet = new Bet();
                bet.setLeft(Double.parseDouble(element1.getText()));
                bet.setRight(Double.parseDouble(element2.getText()));
                return bet;
            }

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

}
