package com.fork.finder;

import com.fork.model.Live;

import java.util.List;

public interface LiveService {

    List<Live> getLives();
    void startLive();
    void clearLives();

}
