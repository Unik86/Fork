package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Match;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class WillMatchParser extends Parser{

    private final static String URL = "http://sports.williamhill.com/bet/en-gb/betting/y/5/tm/1/Football.html";

    public WillMatchParser() {
        log.info("Enter the site " + URL);
        goToSite();
    }

    private void goToSite(){
        driver.manage().window().maximize();
        driver.get(URL);

        try {
            Select timeZone = new Select(driver.findElement(By.name("time_zone")));
            timeZone.selectByVisibleText("Europe/Kiev");
            driver.findElement(By.id("yesBtn")).click();
        } catch (Exception e){

        }

        Select rateFormat = new Select(driver.findElement(By.name("oddsType")));
        rateFormat.selectByVisibleText("Decimal");
    }

    @Override
    public List<Match> parsMainRates(){
        log.info("Pars main rates");

        matchs = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//table[contains(@class, 'tableData')]/tbody/tr"));

        for(WebElement element : elements){
            try {
                WebElement elementName = element.findElement(By.tagName("span"));
                List<WebElement> elementRates = element.findElements(By.className("eventprice"));
                Bet bet = null;

                String[] names = elementName.getText().split(" v ");
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
                match.setPlayerLeft(names[0].trim());
                match.setPlayerRight(names[1].trim());
                match.setWinner(bet);
                matchs.add(match);
            } catch (Exception e){
                log.error("Pars error");
                continue;
            }
        }

        return matchs;
    }

    @Override
    public List<Match> parsAllRates() {
        parsMatches();

        for(Match match : matchs){
            // DELETE
            if(matchs.indexOf(match) > 2)
                return matchs;

            driver.get(match.getUrl());
            parsMatch(match);
        }

        return matchs;
    }

    private void parsMatches(){
        log.info("Pars matches");

        List<WebElement> elements = driver.findElements(By.xpath("//table[contains(@class, 'tableData')]/tbody/tr/td[3]/a"));
        for(WebElement element : elements){
            String[] names = element.getText().split(" v ");
            if(names.length < 2)
                continue;

            Match match = new Match();
            match.setPlayerRight(names[0].trim());
            match.setPlayerLeft(names[1].trim());
            match.setUrl(element.getAttribute("href"));
            matchs.add(match);
        }

        log.info("Matches size = " + matchs.size());
    }

    private Match parsMatch(Match match) {
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
            WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::table/tbody/tr/td[1]/div/div[1]"));
            WebElement element2 = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::table/tbody/tr/td[2]/div/div[1]"));

            try {
                WebElement element3 = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::table/tbody/tr/td[3]/div/div[1]"));

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

}
