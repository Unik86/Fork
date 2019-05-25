package com.fork.base.model;

import java.util.List;

public class ForkResult {

    private List<Fork> forks;
    private List<TwoOfThree> twoOfThrees;

    public List<Fork> getForks() {
        return forks;
    }

    public void setForks(List<Fork> forks) {
        this.forks = forks;
    }

    public List<TwoOfThree> getTwoOfThrees() {
        return twoOfThrees;
    }

    public void setTwoOfThrees(List<TwoOfThree> twoOfThrees) {
        this.twoOfThrees = twoOfThrees;
    }
}
