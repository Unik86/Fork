package com.fork.parser;

import com.fork.dict.NodeDict;
import com.fork.model.Bet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

import static com.fork.util.Utils.fixLengthStr;

public class WillMatchParser implements Parser{

    private WebDriver driver = new ChromeDriver();

    public WillMatchParser(String url) {
        driver.navigate().to(url);
    }

//    @Override
//    public Map<Integer, Node> pars() {
//        List<WebElement> elements = driver.findElements(By.className("tableData"));
//
//        for (WebElement element: elements) {
//            WebElement nameNode = element.findElement(By.xpath("thead//span[contains(@id, 'ip_market_name')]"));
//            String nameNodeStr = nameNode.getText().trim();
//            Integer codeNode = NodeDict.findCode(nameNodeStr);
//
//            if(codeNode == null)
//                continue;
//
//            List<WebElement> elementBets = element.findElements(By.xpath("tbody/tr/td"));
//            List<Bet> bets = new ArrayList<>();
//
//            for (WebElement elBet: elementBets) {
//                WebElement nameBet = elBet.findElement(By.xpath("div/div[2]"));
//                WebElement rateBet = elBet.findElement(By.xpath("div/div[1]"));
//
//                Bet bet = new Bet(nameBet.getText(), Double.parseDouble(rateBet.getText()));
//                bets.add(bet);
//            }
//
//            if(nodes.containsKey(codeNode)){
//                Node node = nodes.get(codeNode);
//                node.getBets().addAll(bets);
//            } else {
//                Node node = new Node(nameNodeStr, bets);
//                nodes.put(codeNode, node);
//            }
//        }
//
//        return nodes;
//    }

}
