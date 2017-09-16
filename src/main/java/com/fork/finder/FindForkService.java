package com.fork.finder;

import com.fork.model.Fork;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;

import java.util.List;

public interface FindForkService {

    void parseAll();
    void countUp();
    void findMatchFork(Fork fork);
    List<Match> getMatches(String type);
    List<Fork> getForks();
    List<TwoOfThree> getTwoOfThrees();

}
