package com.fork.parser;

import com.fork.Mian;
import com.fork.model.Bet;
import com.fork.model.Match;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class WillMatchParser implements Parser{

    private final static Logger logger = Logger.getLogger(WillMatchParser.class);

    private static String URL = "http://sports.williamhill.com/bet/ru";

    public WillMatchParser() {
        logger.info("Enter the site" + URL);
        goToLiveFootballTab();
    }

    private void goToLiveFootballTab(){
        driver.navigate().to(URL);

        try {
            Select timeZone = new Select(driver.findElement(By.name("time_zone")));
            timeZone.selectByVisibleText("Европа/Киев (GMT+2)");
            driver.findElement(By.id("yesBtn")).click();
        } catch (Exception e){

        }

        driver.findElement(By.linkText("Ставки Live")).click();

        Select changeSport = new Select(driver.findElement(By.id("change_sport")));
        changeSport.selectByVisibleText("Футбол");
    }

    @Override
    public void pars() {
        parsLiveMatches();
        for(Match match : matchs){
            parsMatch(match);
        }
        goToLiveFootballTab();
    }

    private void parsLiveMatches(){
        logger.info("Pars live matches");

        List<WebElement> elements = driver.findElements(By.xpath("//table[contains(@class, 'tableData')]/tbody/tr/td[3]/a"));
        for(WebElement element : elements){
            Match match = new Match(element.getText(), element.getAttribute("href"));
            matchs.add(match);
        }
    }

    private Match parsMatch(Match match) {
        driver.navigate().to(match.getUrl());

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
