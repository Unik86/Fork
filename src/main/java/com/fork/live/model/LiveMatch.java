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
    private Bet set1Game1;
    @Getter @Setter
    private Bet set1Game2;
    @Getter @Setter
    private Bet set1Game3;
    @Getter @Setter
    private Bet set1Game4;
    @Getter @Setter
    private Bet set1Game5;
    @Getter @Setter
    private Bet set1Game6;
    @Getter @Setter
    private Bet set1Game7;
    @Getter @Setter
    private Bet set1Game8;
    @Getter @Setter
    private Bet set1Game9;
    @Getter @Setter
    private Bet set1Game10;
    @Getter @Setter
    private Bet set1Game11;
    @Getter @Setter
    private Bet set1Game12;

    @Getter @Setter
    private Bet set2Game1;
    @Getter @Setter
    private Bet set2Game2;
    @Getter @Setter
    private Bet set2Game3;
    @Getter @Setter
    private Bet set2Game4;
    @Getter @Setter
    private Bet set2Game5;
    @Getter @Setter
    private Bet set2Game6;
    @Getter @Setter
    private Bet set2Game7;
    @Getter @Setter
    private Bet set2Game8;
    @Getter @Setter
    private Bet set2Game9;
    @Getter @Setter
    private Bet set2Game10;
    @Getter @Setter
    private Bet set2Game11;
    @Getter @Setter
    private Bet set2Game12;

    @Getter @Setter
    private Bet set3Game1;
    @Getter @Setter
    private Bet set3Game2;
    @Getter @Setter
    private Bet set3Game3;
    @Getter @Setter
    private Bet set3Game4;
    @Getter @Setter
    private Bet set3Game5;
    @Getter @Setter
    private Bet set3Game6;
    @Getter @Setter
    private Bet set3Game7;
    @Getter @Setter
    private Bet set3Game8;
    @Getter @Setter
    private Bet set3Game9;
    @Getter @Setter
    private Bet set3Game10;
    @Getter @Setter
    private Bet set3Game11;
    @Getter @Setter
    private Bet set3Game12;

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
