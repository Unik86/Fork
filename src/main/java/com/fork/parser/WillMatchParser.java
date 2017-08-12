package com.fork.parser;

import com.fork.model.Bet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class WillMatchParser implements Parser{

    public WillMatchParser(String url) {
        driver.navigate().to(url);
    }

    @Override
    public void pars() {
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
    }

    private Bet parsBet(String str) {
        try {
            WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'" + str + "')]/ancestor::table/tbody/tr/td[1]/div/div[1]"));
            WebElement element2 = driver.findElement(By.xpath("//span[contains(text(),'" + str + "')]/ancestor::table/tbody/tr/td[2]/div/div[1]"));

            try {
                WebElement element3 = driver.findElement(By.xpath("//span[contains(text(),'" + str + "')]/ancestor::table/tbody/tr/td[3]/div/div[1]"));

                return new Bet(Double.parseDouble(element1.getText()),
                               Double.parseDouble(element2.getText()),
                               Double.parseDouble(element3.getText()));
            } catch (NoSuchElementException e) {
                return new Bet(Double.parseDouble(element1.getText()),
                               Double.parseDouble(element2.getText()));
            }

        } catch (Exception e) {
            //TODO log
            return null;
        }
    }

}
