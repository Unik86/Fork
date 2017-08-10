package com.fork;

import com.fork.parser.WillMatchParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11524278/%D0%90%D0%BD%D0%B4%D0%B5%D1%80%D0%BB%D0%B5%D1%85%D1%82+%D0%A0%D0%B5%D0%B7%D0%B5%D1%80%D0%B2+v+%D0%9E%D1%81%D1%82%D0%B5%D0%BD%D0%B4%D0%B5+%D0%A0%D0%B5%D0%B7%D0%B5%D1%80%D0%B2.html";

    public static void main(String[] args) {

        WillMatchParser will = new WillMatchParser(WILL_URL);
        will.pars();
        will.print();

    }

}
