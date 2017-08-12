package com.fork.parser;

import org.openqa.selenium.WebDriver;
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

    }

}
