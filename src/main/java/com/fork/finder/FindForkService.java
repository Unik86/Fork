package com.fork.finder;

import com.fork.calc.Fork;
import com.fork.model.Match;
import java.util.List;

public interface FindForkService {

    void findFork();
    List<Match> getMatches(String type);
    List<Fork> getForks();

}
