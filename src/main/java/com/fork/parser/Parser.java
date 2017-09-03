package com.fork.parser;

import com.fork.model.Match;
import java.util.List;

public interface Parser {

    List<Match> parsMainRates();
    List<Match> parsAllRates();
    List<Match> getMatchs();

    void goToSite();
    void closeBrowser();

}
