package com.fork.calc;

import com.fork.model.Fork;
import com.fork.model.Match;

import java.util.List;

public interface MatchService {

    void findForkForMainRates(List<Match>... sites);
    void findFork(Match... matches);
    List<Fork> getForks();

}
