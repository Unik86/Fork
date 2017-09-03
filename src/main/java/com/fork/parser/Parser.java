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

    public void closeBrowser(){
        driver.close();
    }

}
