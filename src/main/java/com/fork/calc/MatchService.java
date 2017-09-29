package com.fork.calc;

import com.fork.model.*;

import java.util.List;

public interface MatchService {

    void findForkForMainRates(List<BookMaker> bookMakers);
    void findFork(List<FullMatch> matches);
    List<Fork> getForks();
    List<TwoOfThree> getTwoOfThrees();

}
