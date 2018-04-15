package com.fork.base.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.fork.util.Utils.dateFormater;

@Document(collection = "Match")
public class Match {

    @Getter @Setter
    private String playerLeft;
    @Getter @Setter
    private String playerRight;
    @Getter @Setter
    private String bookMaker;
    @Getter @Setter
    private String sportType;
    @Getter @Setter
    private String url;
    @Getter @Setter
    private String time;
    @Getter @Setter
    private LocalDateTime parsDate = LocalDateTime.now();

    @Getter @Setter
    private Bet winner;

    public String getParsDateStr(){
        return dateFormater(parsDate);
    }
}
