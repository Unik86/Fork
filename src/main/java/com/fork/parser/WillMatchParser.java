package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Match;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class WillMatchParser extends Parser{

    private final static Logger logger = Logger.getLogger(WillMatchParser.class);

    private final static String URL = "http://sports.williamhill.com/bet/en-gb/betlive/9";

    public WillMatchParser() {
        logger.info("Enter the site " + URL);
        goToLiveFootballTab();
    }

    @Override
    protected void goToLiveFootballTab(){
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
        logger.info("Pars main rates");

        List<WebElement> elements = driver.findElements(By.xpath("//table[contains(@class, 'tableData')]/tbody/tr"));

        for(WebElement element : elements){
            WebElement elementName = element.findElement(By.tagName("span"));
            List<WebElement> elementRates = element.findElements(By.className("eventprice"));
            Bet bet = null;

            String[] names = elementName.getText().split(" v ");
            if(names.length < 2)
                continue;

            if(elementRates.size() == 3)
                bet = new Bet(Double.parseDouble(elementRates.get(0).getText()),
                              Double.parseDouble(elementRates.get(1).getText()),
                              Double.parseDouble(elementRates.get(2).getText()));
            else if(elementRates.size() == 2)
                bet = new Bet(Double.parseDouble(elementRates.get(0).getText()),
                              Double.parseDouble(elementRates.get(1).getText()));
            else
                continue;

            Match match = new Match();
            match.setPlayerLeft(names[0].trim());
            match.setPlayerRight(names[1].trim());
            match.setWinner(bet);
            matchs.add(match);
        }

        return matchs;
    }

    @Override
    protected void parsLiveMatches(){
        logger.info("Pars live matches");

        List<WebElement> elements = driver.findElements(By.xpath("//table[contains(@class, 'tableData')]/tbody/tr/td[3]/a"));
        for(WebElement element : elements){
            String[] names = element.getText().split(" v ");
            if(names.length < 2)
                continue;

            Match match = new Match();
            match.setPlayerRight(names[0].trim());
            match.setPlayerLeft(names[1].trim());
            matchs.add(match);
        }
    }

    @Override
    protected Match parsMatch(Match match) {
        match.setWinner(parsBet("Победитель встречи Live"));

        match.setTotal05(parsBet("Игра - Больше/Меньше 0.5 голов Live"));
        match.setTotal15(parsBet("Игра - Больше/Меньше 1.5 голов Live"));
        match.setTotal25(parsBet("Игра - Больше/Меньше 2.5 голов Live"));
        match.setTotal35(parsBet("Игра - Больше/Меньше 3.5 голов Live"));
        match.setTotal45(parsBet("Игра - Больше/Меньше 4.5 голов Live"));
        match.setTotal55(parsBet("Игра - Больше/Меньше 5.5 голов Live"));
        match.setTotal65(parsBet("Игра - Больше/Меньше 6.5 голов Live"));
        match.setTotal75(parsBet("Игра - Больше/Меньше 7.5 голов Live"));
        match.setTotal85(parsBet("Игра - Больше/Меньше 8.5 голов Live"));
        match.setTotal95(parsBet("Игра - Больше/Меньше 9.5 голов Live"));

        return match;
    }

    private Bet parsBet(String name) {
        try {
            WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::table/tbody/tr/td[1]/div/div[1]"));
            WebElement element2 = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::table/tbody/tr/td[2]/div/div[1]"));

            try {
                WebElement element3 = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::table/tbody/tr/td[3]/div/div[1]"));

                return new Bet(Double.parseDouble(element1.getText()),
                               Double.parseDouble(element2.getText()),
                               Double.parseDouble(element3.getText()));
            } catch (NoSuchElementException e) {
                return new Bet(Double.parseDouble(element1.getText()),
                               Double.parseDouble(element2.getText()));
            }

        } catch (Exception e) {
            return null;
        }
    }

}
