package com.fork.parser;

import com.fork.model.Bet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FavMatchParser implements Parser{

    private WebDriver driver = new ChromeDriver();

    public FavMatchParser(String url) {
        try {
            driver.navigate().to(url);
            Thread.sleep(6000);
            driver.navigate().to(url);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pars() {
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
            //TODO log
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
            //TODO log
            return null;
        }
    }
}
