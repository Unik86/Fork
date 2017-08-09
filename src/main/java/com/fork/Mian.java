package com.fork;

import com.fork.parser.WillMatchParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11262530/%D0%A8%D0%B5%D1%84%D1%84%D0%B8%D0%BB%D0%B4+%D0%AE%D0%BD%D0%B0%D0%B9%D1%82%D0%B5%D0%B4+v+%D0%A3%D0%BE%D0%BB%D1%81%D0%BE%D0%BB%D0%BB.html";

    public static void main(String[] args) {

        WillMatchParser will = new WillMatchParser(WILL_URL);
        will.pars();
        will.print();

    }

}
