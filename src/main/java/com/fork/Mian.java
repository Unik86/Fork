package com.fork;

import com.fork.parser.WillMatchParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11417407/%D0%9B%D0%BE%D0%B7%D0%B0%D0%BD%D0%BD%D0%B0+v+%D0%9B%D1%83%D0%B3%D0%B0%D0%BD%D0%BE.html";

    public static void main(String[] args) {
//        WillMatchParser will = new WillMatchParser(WILL_URL);
//        will.pars();
//        will.print();

        WebDriver driver = new ChromeDriver();
        driver.navigate().to(WILL_URL);

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        driver.findElement(By.id("yesBtn")).click();

        List<WebElement> rows = driver.findElements(By.className("tableData"));

//        System.out.println(rows);

        for (WebElement row : rows){
//            System.out.println(row.findElement(By.xpath("span[@id='market']")));

//            System.out.println(row.findElement(By.xpath("//*[contains(text(), 'Больше/Меньше')]")).getText());
            List<WebElement> rows2 = row.findElements(By.xpath("//span[contains(@id, 'ip_market_name_')]"));

            for (WebElement row2 : rows2){
                System.out.println(row2.getText());
            }

        }

//        driver.close();

    }

}
