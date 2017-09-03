package com.fork.parser;

import com.fork.model.Bet;
import com.fork.model.Match;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MockParser")
public class MockParser extends BaseParser {

    public MockParser() {
        Match match1 = new Match();
        match1.setPlayerLeft("TestLeft1");
        match1.setPlayerRight("TestRight1");
        match1.setUrl("http://fk.com/");
        Bet bet1 = new Bet();
        bet1.setLeft(1.07);
        bet1.setCenter(2.1);
        bet1.setRight(3.25);
        match1.setWinner(bet1);
        matchs.add(match1);

        Match match2 = new Match();
        match2.setPlayerLeft("TestLeft2");
        match2.setPlayerRight("TestRight2");
        match2.setUrl("http://fk.com/");
        Bet bet2 = new Bet();
        bet2.setLeft(1.5);
        bet2.setCenter(2.6);
        bet2.setRight(4.55);
        match2.setWinner(bet2);
        matchs.add(match2);

        Match match3 = new Match();
        match3.setPlayerLeft("TestLeft3");
        match3.setPlayerRight("TestRight3");
        match3.setUrl("http://fk.com/");
        Bet bet3 = new Bet();
        bet3.setLeft(1.75);
        bet3.setCenter(2.9);
        bet3.setRight(5.0);
        match3.setWinner(bet3);
        matchs.add(match3);
    }

    @Override
    public List<Match> parsMainRates() {
        return null;
    }

    @Override
    public List<Match> parsAllRates() {
        return null;
    }

    @Override
    public void goToSite() {

    }
}
