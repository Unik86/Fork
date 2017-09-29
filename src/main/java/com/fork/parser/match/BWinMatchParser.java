package com.fork.parser.match;

import com.fork.model.Bet;
import com.fork.model.enums.BookMakers;
import com.fork.model.FullMatch;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Log4j
@Component("BWinMatch")
public class BWinMatchParser implements MatchParser{

    private final static String URL = "https://livebetting.bwin.com/en/live#/6481914";

    private WebDriver driver;

    public void goToSite() {
        log.info("Enter the site " + getBookMakerName());

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(URL);

            Thread.sleep(1000);
        } catch (Exception e){
            driver.close();
            log.error("Enter the site failure");
        }
    }

    @Override
    public FullMatch parsMatch() {
        log.info("Pars Match");

        FullMatch match = new FullMatch(getBookMakerName());

        match.setWinner(parsBet("Match Result"));

        match.setTotal05(parsBet("Total Goals - Over/Under", "0,5"));
        match.setTotal15(parsBet("Total Goals - Over/Under", "1,5"));
        match.setTotal25(parsBet("Total Goals - Over/Under", "2,5"));
        match.setTotal35(parsBet("Total Goals - Over/Under", "3,5"));
        match.setTotal45(parsBet("Total Goals - Over/Under", "4,5"));
        match.setTotal55(parsBet("Total Goals - Over/Under", "5,5"));
        match.setTotal65(parsBet("Total Goals - Over/Under", "6,5"));
        match.setTotal75(parsBet("Total Goals - Over/Under", "7,5"));
        match.setTotal85(parsBet("Total Goals - Over/Under", "8,5"));
        match.setTotal95(parsBet("Total Goals - Over/Under", "9,5"));

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

            switch (elements.size()) {
                case 0:
                case 1:
                    bet = null;
                    break;
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
                    bet.setName(name + " " + subName);

                    elements = findBetRates(elements, subName);
                    bet.setLeft(Double.parseDouble(getBetRate(elements.get(1))));
                    bet.setRight(Double.parseDouble(getBetRate(elements.get(0))));
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
