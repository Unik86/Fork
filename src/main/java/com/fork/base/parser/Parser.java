package com.fork.base.parser;

import com.fork.base.model.BookMaker;

public interface Parser {

    void parsMainRates();
    BookMaker getBookMaker();
    void setParseType(String parseType);

    void goToSite() throws Exception;
    void closeBrowser();

}
