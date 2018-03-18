package com.fork.parser;

import com.fork.model.FullMatch;

public interface MatchParser {

    FullMatch parsMatch();
    void goToSite() throws Exception;
    void closeBrowser();

}
