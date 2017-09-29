package com.fork.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Match")
public class Match {

    @Getter @Setter
    private String playerLeft;
    @Getter @Setter
    private String playerRight;
    @Getter @Setter
    private String bookMaker;
    @Getter @Setter
    private String url;
    @Getter @Setter
    private String time;

    @Getter @Setter
    private Bet winner;

}
