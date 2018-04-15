package com.fork.live.parser;

import com.fork.live.model.LiveMatch;

public interface LiveParser {

    LiveMatch parsMatch();
    void goToSite() throws Exception;
    void closeBrowser();

}
