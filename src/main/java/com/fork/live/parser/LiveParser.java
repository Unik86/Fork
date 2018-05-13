package com.fork.live.parser;

import com.fork.live.model.LiveMatch;

public interface LiveParser {

    LiveMatch parsMatch();
    void goToSite(String url) throws Exception;
    void closeBrowser();

}
