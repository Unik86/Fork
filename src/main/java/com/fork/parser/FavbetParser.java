package com.fork.parser;

import com.fork.model.Match;
import com.fork.model.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavbetParser {

    private Document doc;
    private List<Match> matchs = new ArrayList<Match>();

    public FavbetParser(String url) {
        try {
            doc = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .timeout(10000)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
//        for (Match match: matchs) {
//            Player one = match.getOne();
//            Player two = match.getTwo();
//
//            printCell(Integer.toString(one.getScore()));
//            printCell(Integer.toString(two.getScore()));
//            printCell(fixLengthStr(one.getName(), 30));
//            printCell(fixLengthStr(two.getName(), 30));
//            printCell(fixLengthStr(Double.toString(one.getPrice()), 7));
//            printCell(fixLengthStr(Double.toString(two.getPrice()), 7));
//            printCell(match.getLink());
//
//            System.out.println();
//        }
    }

    public void pars() {
        Elements rows = doc.select(".disclaim");

        System.out.println(rows);

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

    private void printCell(String cell) {
        System.out.print(cell);
        System.out.print(" | ");
    }

    private String fixLengthStr(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
}
