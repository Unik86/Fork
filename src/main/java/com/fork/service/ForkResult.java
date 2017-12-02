package com.fork.service;

import com.fork.model.Fork;
import com.fork.model.TwoOfThree;
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
