package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Match;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fork.util.Utils.fixLengthStr;

public abstract class Parser {

    protected WebDriver driver = new ChromeDriver();
    protected List<Match> matchs = new ArrayList<>();
    protected Map<String, String> tabs = new HashMap<>();

    protected abstract void parsLiveMatches();

    protected abstract Match parsMatch(Match match);

    protected abstract void goToLiveFootballTab();

    public abstract void parsMainRates();

    public void parsAllRates() {
        parsLiveMatches();

        for(Match match : matchs){
            // DELETE
            if(matchs.indexOf(match) > 2)
                return;

            openTab(match);
            parsMatch(match);
        }

        goToLiveFootballTab();
    }

    private void openTab(Match match){

        if(tabs.containsKey(match.getUrl())) {
            driver.switchTo().window(tabs.get(match.getUrl()));
            return;
        }

        ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
        ArrayList<String> browserTabs = new ArrayList<> (driver.getWindowHandles());
        String browserTab = browserTabs.get(browserTabs.size() - 1);
        driver.switchTo().window(browserTab);
        driver.get(match.getUrl());
        tabs.put(match.getUrl(), browserTab);
    }

    public void print() {
        for (Match match : matchs) {
            for (Field field : match.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);

                    Object obj = field.get(match);

                    if(obj == null)
                        continue;

                    if(!(obj instanceof Bet)) {
                        System.out.println("-----" + obj);
                        continue;
                    }

                    Bet bet = (Bet) obj;

                    System.out.println();
                    System.out.println(field.getName());

                    System.out.print(fixLengthStr(bet.getLeft().toString(), 5));
                    System.out.print(" | ");

                    if (bet.getCenter() != null) {
                        System.out.print(fixLengthStr(bet.getCenter().toString(), 5));
                        System.out.print(" | ");
                    }
                    System.out.print(fixLengthStr(bet.getRight().toString(), 5));

                    System.out.println();
                    System.out.println();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
