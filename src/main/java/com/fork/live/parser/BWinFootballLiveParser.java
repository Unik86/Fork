package com.fork.live.parser;

import com.fork.base.model.Bet;
import com.fork.base.model.enums.BookMakers;
import com.fork.live.model.LiveMatch;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Log4j
@Component("BWinFootballLive")
public class BWinFootballLiveParser implements LiveParser {

    private final static String URL = "https://livebetting.bwin.com/ru/live#/7162987";

    private WebDriver driver;

    public void goToSite() throws Exception{
        log.info("Enter the site " + getBookMakerName());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        Thread.sleep(1000);
    }

    @Override
    public LiveMatch parsMatch() {
        log.info("Pars Match");

        LiveMatch match = new LiveMatch(getBookMakerName());

        match.setWinner(parsBet("1 Х 2"));

        match.setTotal05(parsBet("Сколько голов будет забито?", "0,5"));
        match.setTotal15(parsBet("Сколько голов будет забито?", "1,5"));
        match.setTotal25(parsBet("Сколько голов будет забито?", "2,5"));
        match.setTotal35(parsBet("Сколько голов будет забито?", "3,5"));
        match.setTotal45(parsBet("Сколько голов будет забито?", "4,5"));
        match.setTotal55(parsBet("Сколько голов будет забито?", "5,5"));
        match.setTotal65(parsBet("Сколько голов будет забито?", "6,5"));
        match.setTotal75(parsBet("Сколько голов будет забито?", "7,5"));
        match.setTotal85(parsBet("Сколько голов будет забито?", "8,5"));
        match.setTotal95(parsBet("Сколько голов будет забито?", "9,5"));

        return match;
    }

    private Bet parsBet(String name) {
        return parsBet(name, null);
    }

    private Bet parsBet(String name, String subName) {
        try {
            List<WebElement> elements = driver
                    .findElements(By.xpath("//span[text()='" + name + "']/ancestor::div[contains(@class, 'markets')]/ul/li"));
            Bet bet = new Bet();
            bet.setBookMaker(getBookMakerName());

            if(nonNull(subName)){
                bet.setName(name + " " + subName);

                elements = findBetRates(elements, subName);

                if(isNull(elements) || elements.size() < 2)
                    return null;

                bet.setLeft(Double.parseDouble(getBetRate(elements.get(1))));
                bet.setRight(Double.parseDouble(getBetRate(elements.get(0))));

                return bet;
            }

            switch (elements.size()) {
                case 2:
                    bet.setName(name);

                    bet.setLeft(Double.parseDouble(getBetRate(elements.get(0))));
                    bet.setRight(Double.parseDouble(getBetRate(elements.get(1))));
                    break;
                case 3:
                    bet.setName(name);

                    bet.setLeft(Double.parseDouble(getBetRate(elements.get(0))));
                    bet.setCenter(Double.parseDouble(getBetRate(elements.get(1))));
                    bet.setRight(Double.parseDouble(getBetRate(elements.get(2))));
                    break;
                default:
                    return null;
            }

            return bet;
        } catch (Exception e) {
            log.error("Pars Error");
            return null;
        }
    }

    private List<WebElement> findBetRates(List<WebElement> elements, String subName){
        List<WebElement> result = new ArrayList<>();

        for(WebElement element : elements) {
            String label = element.findElement(By.className("name")).getText();
            if(label.contains(subName))
                result.add(element);
        }

        return result;
    }

    private String getBetRate(WebElement element){
        return element.findElement(By.className("odds")).getText();
    }

    @Override
    public void closeBrowser() {
        log.info("Close browser");
        if(nonNull(driver))
            driver.close();
    }

    private String getBookMakerName(){
        return BookMakers.BWIN.getName();
    }

}
