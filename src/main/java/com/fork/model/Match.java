package com.fork.model;

import lombok.Getter;
import lombok.Setter;

public class Match {

    @Getter @Setter
    private String playerLeft;
    @Getter @Setter
    private String playerRight;
    @Getter @Setter
    private String url;
    @Getter @Setter
    private String time;

    @Getter @Setter
    private Bet winner;

    @Getter @Setter
    private Bet total05;
    @Getter @Setter
    private Bet total15;
    @Getter @Setter
    private Bet total25;
    @Getter @Setter
    private Bet total35;
    @Getter @Setter
    private Bet total45;
    @Getter @Setter
    private Bet total55;
    @Getter @Setter
    private Bet total65;
    @Getter @Setter
    private Bet total75;
    @Getter @Setter
    private Bet total85;
    @Getter @Setter
    private Bet total95;

}
