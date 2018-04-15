package com.fork.live.model;

import com.fork.base.model.Bet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LiveMatch")
public class LiveMatch {

    @Getter @Setter
    private String bookMaker;

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

    public LiveMatch(String bookMaker) {
        this.bookMaker = bookMaker;
    }

}
