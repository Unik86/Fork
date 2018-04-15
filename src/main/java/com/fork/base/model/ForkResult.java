package com.fork.base.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ForkResult {

    @Getter
    @Setter
    private List<Fork> forks;
    @Getter
    @Setter
    private List<TwoOfThree> twoOfThrees;

}
