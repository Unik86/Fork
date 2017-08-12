package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Match;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.reflect.Field;

import static com.fork.util.Utils.fixLengthStr;

public interface Parser {

    WebDriver driver = new ChromeDriver();
    Match match = new Match();

    public void pars();

    public default void print() {
        for (Field field : match.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Bet bet = (Bet) field.get(match);

                if(bet == null)
                    continue;

                System.out.println();
                System.out.println("*** " + field.getName() + " ***");
                System.out.println();

                System.out.print(fixLengthStr(bet.getLeft().toString(), 5));
                System.out.print(" | ");

                if(bet.getCenter() != null) {
                    System.out.print(fixLengthStr(bet.getCenter().toString(), 5));
                    System.out.print(" | ");
                }
                System.out.print(fixLengthStr(bet.getRight().toString(), 5));

                System.out.println();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
