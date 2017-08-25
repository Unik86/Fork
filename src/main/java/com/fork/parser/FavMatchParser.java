package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Match;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class FavMatchParser extends Parser{

    private final static String URL = "https://www.favbet.com/en/bets/#tours=17296";

    public FavMatchParser() {
        log.info("Enter the site " + URL);
        goToSite();
    }

    protected void goToSite(){
        try {
            driver.manage().window().maximize();
            driver.navigate().to(URL);
            Thread.sleep(6000);
            driver.navigate().to(URL);
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Match> parsMainRates(){
        log.info("Pars main rates");

        matchs = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'event--head-block')]"));

        for(WebElement element : elements){
            try{
                WebElement elementName = element.findElement(By.className("event--name"));
                WebElement elementRates = element.findElement(By.className("count-0"));

                String[] names = elementName.getText().split("\n");
                if(names.length < 2)
                    continue;

                String[] rates = elementRates.getText().split("\n");
                Bet bet = null;

                if(rates.length == 3) {
                    bet = new Bet();
                    bet.setLeft(Double.parseDouble(rates[0]));
                    bet.setCenter(Double.parseDouble(rates[1]));
                    bet.setRight(Double.parseDouble(rates[2]));
                } else if(rates.length == 2) {
                    bet = new Bet();
                    bet.setLeft(Double.parseDouble(rates[0]));
                    bet.setRight(Double.parseDouble(rates[1]));
                } else
                    continue;

                Match match = new Match();
                match.setPlayerLeft(names[0]);
                match.setPlayerRight(names[1]);
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
        return matchs;
    }

    private void parsMatches(){
        log.info("Pars matches");

        List<WebElement> elements = driver.findElements(By.xpath("//ul[contains(@class, 'category--list--block')]//div[contains(@class, 'event--name--info')]"));
        try {
            for(WebElement element : elements){
                // DELETE
                if(elements.indexOf(element) > 2)
                    continue;

                element.click();

                String[] names = element.getText().split("\n");
                if(names.length < 2)
                    continue;

                Match match = new Match();
                match.setPlayerLeft(names[0]);
                match.setPlayerRight(names[1]);
                match.setUrl(driver.getCurrentUrl());

                parsMatch(match);
                matchs.add(match);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        log.info("Matches size = " + matchs.size());
    }

    private Match parsMatch(Match match) {
        match.setWinner(parsBet("Победа"));

        match.setTotal05(parsComplexBet("Over/Under", "Over (0.5)", "Under (0.5)"));
        match.setTotal15(parsComplexBet("Over/Under", "Over (1.5)", "Under (1.5)"));
        match.setTotal25(parsComplexBet("Over/Under", "Over (2.5)", "Under (2.5)"));
        match.setTotal35(parsComplexBet("Over/Under", "Over (3.5)", "Under (3.5)"));
        match.setTotal45(parsComplexBet("Over/Under", "Over (4.5)", "Under (4.5)"));
        match.setTotal55(parsComplexBet("Over/Under", "Over (5.5)", "Under (5.5)"));
        match.setTotal65(parsComplexBet("Over/Under", "Over (6.5)", "Under (6.5)"));
        match.setTotal75(parsComplexBet("Over/Under", "Over (7.5)", "Under (7.5)"));
        match.setTotal85(parsComplexBet("Over/Under", "Over (8.5)", "Under (8.5)"));
        match.setTotal95(parsComplexBet("Over/Under", "Over (9.5)", "Under (9.5)"));

        return match;
    }

    private Bet parsComplexBet(String baseName, String LeftName, String RightName) {
        try {
            //div[text()='Победа']/ancestor::li/ul/li/div/div
            WebElement element1 = driver.findElement(By.xpath("//div[text()='"
                    + baseName +"']/ancestor::li/ul/li/div/ul/li/label/span[text()='"
                    + LeftName + "']/following-sibling::button"));
            WebElement element2 = driver.findElement(By.xpath("//div[text()='"
                    + baseName +"']/ancestor::li/ul/li/div/ul/li/label/span[text()='"
                    + RightName + "']/following-sibling::button"));

            Bet bet = new Bet();
            bet.setLeft(Double.parseDouble(element1.getText()));
            bet.setRight(Double.parseDouble(element2.getText()));
            return bet;

        } catch (Exception e) {
            log.error("Pars bet error");
            return null;
        }
    }

    private Bet parsBet(String str) {
        try {
            //div[text()='Победа']/ancestor::li/ul/li/div/div
            WebElement element1 = driver.findElement(By.xpath("//div[text()='" + str +"']/ancestor::li/ul/li/div/ul/li[1]/label/button"));
            WebElement element2 = driver.findElement(By.xpath("//div[text()='" + str +"']/ancestor::li/ul/li/div/ul/li[2]/label/button"));

            try {
                WebElement element3 = driver.findElement(By.xpath("//div[text()='" + str +"']/ancestor::li/ul/li/div/ul/li[3]/label/button"));

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
            log.error("Pars bet error");
            return null;
        }
    }
}
