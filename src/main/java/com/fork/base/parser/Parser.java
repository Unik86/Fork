package com.fork.base.parser;

import com.fork.base.model.BookMaker;

public interface Parser {

    void parsMainRates();
    BookMaker getBookMaker();

    void goToSite() throws Exception;
    void closeBrowser();

}
