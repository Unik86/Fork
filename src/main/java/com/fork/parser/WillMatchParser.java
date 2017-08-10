package com.fork.parser;

import com.fork.dict.NodeDict;
import com.fork.model.Bet;
import com.fork.model.Node;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class WillMatchParser {

    private WebDriver driver = new ChromeDriver();
    private Map<Integer, Node> nodes = new HashMap<>();

    public WillMatchParser(String url) {
        driver.navigate().to(url);
    }

    public void print() {
        nodes.forEach((k,v) -> {
            System.out.println();
            System.out.println("*** " + v.getName() + " ***");
            System.out.println();

            for (Bet bet : v.getBets()) {
                System.out.print(fixLengthStr(bet.getName() + " - " + Double.toString(bet.getRate()), 20));
                System.out.print(" | ");
            }

            System.out.println();
        });
    }

    public void pars() {
        List<WebElement> elements = driver.findElements(By.className("tableData"));

        for (WebElement element: elements) {
            WebElement nameNode = element.findElement(By.xpath("thead//span[contains(@id, 'ip_market_name')]"));
            String nameNodeStr = nameNode.getText().trim();
            Integer codeNode = NodeDict.findCode(nameNodeStr);

            if(codeNode == null)
                continue;

            List<WebElement> elementBets = element.findElements(By.xpath("tbody/tr/td"));
            List<Bet> bets = new ArrayList<>();

            for (WebElement elBet: elementBets) {
//                WebElement nameBet = elBet.findElement(By.xpath("div/div[contains(@id, 'name')]"));
//                WebElement rateBet = elBet.findElement(By.xpath("div/div[contains(@id, 'price')]"));

                // TODO костыль потом убрать
                List<String> res = Arrays.asList(elBet.getText().split("\n"));
                if(!res.isEmpty() && res.size() > 1) {
                    Bet bet = new Bet(res.get(1), Double.parseDouble(res.get(0)));
                    bets.add(bet);
                }
            }

            Node node = new Node(nameNodeStr, bets);
            nodes.put(codeNode, node);
        }

        driver.close();
    }

    private String fixLengthStr(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
}
