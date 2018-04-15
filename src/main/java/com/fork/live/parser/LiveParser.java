package com.fork.base.parser;

import com.fork.live.model.FullMatch;

public interface MatchParser {

    FullMatch parsMatch();
    void goToSite() throws Exception;
    void closeBrowser();

}
