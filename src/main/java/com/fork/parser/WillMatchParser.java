package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Node;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
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
//        Elements rows = doc.select(".tableData .rowLive");
//
//        for (Element row: rows) {
//            Player one = new Player();
//            Player two = new Player();
//            Match match = new Match(one, two);
//            List<String> list;
//
//            list = parsSplitCell(row, "a[id~=^ip_[0-9]+_score$]", "-");
//            one.setScore(Integer.parseInt(list.get(0)));
//            two.setScore(Integer.parseInt(list.get(1)));
//
//            list = parsSplitCell(row, "span[id~=^[0-9]+_mkt_namespace$]", " v ");
//            one.setName(list.get(0));
//            two.setName(list.get(1));
//
//            list = parsSplitCell(row, "div[id~=^ip_selection[0-9]+price$]", " ");
//            one.setPrice(Double.parseDouble(list.get(0)));
//            two.setPrice(Double.parseDouble(list.get(1)));
//
//            match.setLink(parsLinkCell(row));
//
//            matchs.add(match);
//        }
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
