package com.fork.base.parser;

import com.fork.base.model.BookMaker;

public interface Parser {

    void parsMainRates(String parseType);
    BookMaker getBookMaker();

    void goToSite() throws Exception;
    void closeBrowser();

}
