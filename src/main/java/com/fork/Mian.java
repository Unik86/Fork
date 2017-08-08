package com.fork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Mian {

    public static void main(String[] args) {
//        Parser parser = new Parser("http://sports.williamhill.com/bet/ru/betlive/24");
//        parser.pars();
//        parser.print();

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://new.favoritsport.com.ua/ru/live/#sports=1,2");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> allAuthors =  driver.findElements(By.className("outcome"));

        for (WebElement row : allAuthors){
            System.out.println(row.getText());
        }

        driver.close();

    }

}
