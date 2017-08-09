package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Node;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WillMatchParser {

    private WebDriver driver = new ChromeDriver();
    private List<Node> nodes = new ArrayList<Node>();

    public WillMatchParser(String url) {
        driver.navigate().to(url);
    }

    public void print() {
        for (Node node: nodes) {
            System.out.println(node.getName());

            for (Bet bet : node.getBets()) {
                System.out.print(bet.getName() + " - " + Double.toString(bet.getRate()));
                System.out.print("|");
            }

            System.out.println();
        }
    }

    public void pars() {
        List<WebElement> elements = driver.findElements(By.xpath("//table[@class='tableData']"));

        for (WebElement element: elements) {
//            List<WebElement> nodes = element.findElements(By.cssSelector(".//*"));
            WebElement nameNode = element.findElement(By.xpath("thead//span[contains(@id, 'ip_market_name')]"));
            System.out.println(nameNode.getText());

            List<WebElement> bets = element.findElements(By.xpath("tbody/tr/td"));
            for (WebElement bet: bets) {
                System.out.println(bet.getText());
                System.out.println("++++++");
            }
            System.out.println("----------------");
        }

        driver.close();
    }

    private List<String> parsSplitCell(Element row, String selector, String split) {
        List<String> list = Arrays.asList(row.select(selector).text().split(split));
        String one = list.get(0).replace(String.valueOf((char) 160), " ").trim();
        String two = list.get(1).replace(String.valueOf((char) 160), " ").trim();

        List<String> result = new ArrayList<String>();
        result.add(one);
        result.add(two);
        return result;
    }

    private String parsLinkCell(Element row) {
        return row.select("a[href]").attr("href");
    }

    private String fixLengthStr(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
}
