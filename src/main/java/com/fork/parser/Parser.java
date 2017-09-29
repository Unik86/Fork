package com.fork.parser;

import com.fork.model.BookMaker;
import com.fork.model.Match;
import java.util.List;

public interface Parser {

    void parsMainRates();
    BookMaker getBookMaker();

    void goToSite();
    void closeBrowser();

}
