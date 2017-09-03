package com.fork.finder;

import com.fork.model.Match;

import java.util.List;

public interface FindForkService {

    public void findFork();
    public List<Match> getWillMatches();

}
