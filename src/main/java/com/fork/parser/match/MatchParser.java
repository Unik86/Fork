package com.fork.parser.match;

import com.fork.model.FullMatch;

public interface MatchParser {

    FullMatch parsMatch();
    void goToSite();
    void closeBrowser();

}
