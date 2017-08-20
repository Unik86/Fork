package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Match;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FavMatchParser extends Parser{

    private final static Logger logger = Logger.getLogger(FavMatchParser.class);

    private final static String URL = "https://www.favbet.com/en/live/#sports=1";

    public FavMatchParser() {
        logger.info("Enter the site " + URL);
        goToLiveFootballTab();
    }

    @Override
    protected void goToLiveFootballTab(){
        try {
            driver.manage().window().maximize();
            driver.navigate().to(URL);
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Match> parsMainRates(){
        logger.info("Pars main rates");

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

                if(rates.length == 3)
                    bet = new Bet(Double.parseDouble(rates[0]),
                                  Double.parseDouble(rates[1]),
                                  Double.parseDouble(rates[2]));
                else if(rates.length == 2)
                    bet = new Bet(Double.parseDouble(rates[0]),
                                  Double.parseDouble(rates[1]));
                else
                    continue;

                Match match = new Match();
                match.setPlayerLeft(names[0]);
                match.setPlayerRight(names[1]);
                match.setWinner(bet);
                matchs.add(match);
            } catch (Exception e){
                logger.error("Pars error");
                continue;
            }
        }

        return matchs;
    }

    @Override
    protected void parsLiveMatches(){
        logger.info("Pars live matches");

//        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'event--head-block')]"
//                + "//div[contains(@class, 'event--name two--name')]"));

        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@class, 'sprt sport sp_1 open')]/ul/li/div[1]"));
        try {
            for(WebElement element : elements){
                element.click();

                String[] names = element.getText().split("\n");
                if(names.length < 2)
                    continue;

                Match match = new Match();
                match.setPlayerRight(names[0]);
                match.setPlayerLeft(names[1]);
                match.setUrl(driver.getCurrentUrl());
                matchs.add(match);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected Match parsMatch(Match match) {
        match.setWinner(parsBet("Победа"));

        match.setTotal05(parsComplexBet("Тотал", "Больше (0.5)", "Меньше (0.5)"));
        match.setTotal15(parsComplexBet("Тотал", "Больше (1.5)", "Меньше (1.5)"));
        match.setTotal25(parsComplexBet("Тотал", "Больше (2.5)", "Меньше (2.5)"));
        match.setTotal35(parsComplexBet("Тотал", "Больше (3.5)", "Меньше (3.5)"));
        match.setTotal45(parsComplexBet("Тотал", "Больше (4.5)", "Меньше (4.5)"));
        match.setTotal55(parsComplexBet("Тотал", "Больше (5.5)", "Меньше (5.5)"));
        match.setTotal65(parsComplexBet("Тотал", "Больше (6.5)", "Меньше (6.5)"));
        match.setTotal75(parsComplexBet("Тотал", "Больше (7.5)", "Меньше (7.5)"));
        match.setTotal85(parsComplexBet("Тотал", "Больше (8.5)", "Меньше (8.5)"));
        match.setTotal95(parsComplexBet("Тотал", "Больше (9.5)", "Меньше (9.5)"));

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

            return new Bet(Double.parseDouble(element1.getText()),
                           Double.parseDouble(element2.getText()));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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

                return new Bet(Double.parseDouble(element1.getText()),
                        Double.parseDouble(element2.getText()),
                        Double.parseDouble(element3.getText()));
            } catch (NoSuchElementException e) {
                return new Bet(Double.parseDouble(element1.getText()),
                        Double.parseDouble(element2.getText()));
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
