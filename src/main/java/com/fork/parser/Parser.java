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

    public abstract List<Match> parsMainRates();

    public abstract List<Match> parsAllRates();

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
