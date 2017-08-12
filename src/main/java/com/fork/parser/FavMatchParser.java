package com.fork.parser;

import com.fork.dict.NodeDict;
import com.fork.model.Bet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

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

//    @Override
//    public Map<Integer, Node> pars() {
//        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@class, 'markets')]"));
//
//        for (WebElement element: elements) {
//            WebElement nameNode = element.findElement(By.xpath("div[2]"));
//            String nameNodeStr = nameNode.getText().trim();
//            Integer codeNode = NodeDict.findCode(nameNodeStr);
//
//            if(codeNode == null)
//                continue;
//
//            List<WebElement> elementBets = element.findElements(By.xpath("ul/li/div[1]//li[contains(@class, 'outcome')]"));
//            List<Bet> bets = new ArrayList<>();
//
//            for (WebElement elBet: elementBets) {
//                WebElement nameBet = elBet.findElement(By.xpath("label/span"));
//                WebElement rateBet = elBet.findElement(By.xpath("label/button"));
//
//                Bet bet = new Bet(nameBet.getText(), Double.parseDouble(rateBet.getText()));
//                bets.add(bet);
//            }
//
//            Node node = new Node(nameNodeStr, bets);
//            nodes.put(codeNode, node);
//        }
//
//        return nodes;
//    }

}
