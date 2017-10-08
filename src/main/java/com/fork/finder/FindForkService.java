package com.fork.finder;

import com.fork.model.Fork;
import com.fork.model.Match;
import com.fork.model.TwoOfThree;
import com.fork.model.enums.SportTypes;

import java.util.List;

public interface FindForkService {

    void setSportType(SportTypes type);
    void parseAll();
    void parseBookMaker(String type);
    void countUp();
    void findMatchFork(Fork fork);
    List<Match> getMatches(String type);
    List<Fork> getForks();
    List<TwoOfThree> getTwoOfThrees();

}
