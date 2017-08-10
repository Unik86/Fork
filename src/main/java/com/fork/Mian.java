package com.fork;

import com.fork.parser.WillMatchParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Mian {

    public static String WILL_URL = "http://sports.williamhill.com/bet/ru/betting/e/11524274/Brodd+v+Sandnes+Ulf+2.html";

    public static void main(String[] args) {

        WillMatchParser will = new WillMatchParser(WILL_URL);
        will.pars();
        will.print();

    }

}
