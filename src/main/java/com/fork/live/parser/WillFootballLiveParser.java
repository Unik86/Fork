package com.fork.live.parser;

import com.fork.base.model.*;
import com.fork.base.model.enums.BookMakers;
import com.fork.live.model.LiveMatch;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Log4j
@Component("WillFootballLive")
public class WillFootballLiveParser implements LiveParser {

    private final static String URL = "http://sports.williamhill.com/bet/ru/betting/e/12718307/%D0%9E%D0%BB%D0%B8%D0%BC%D0%BF%D0%B8%D0%BA+v+%D0%90%D0%BB%D0%B5%D0%BA%D1%81%D0%B0%D0%BD%D0%B4%D1%80%D0%B8%D1%8F.html";

    private WebDriver driver;

    @Override
    public void goToSite() throws Exception{
        log.info("Enter the site " + getBookMakerName());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        Select timeZone = new Select(driver.findElement(By.name("time_zone")));
        timeZone.selectByVisibleText("Европа/Киев (GMT+2)");
        driver.findElement(By.id("yesBtn")).click();

        Select rateFormat = new Select(driver.findElement(By.name("oddsType")));
        rateFormat.selectByVisibleText("Десятичный");

        Thread.sleep(1000);
    }

    @Override
    public LiveMatch parsMatch() {
        log.info("Pars Match");

        LiveMatch match = new LiveMatch(getBookMakerName());

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
