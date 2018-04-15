package com.fork.base.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bet")
public class Bet {

    @Getter @Setter
    private String bookMaker;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Double left;

    @Getter @Setter
    private Double center;

    @Getter @Setter
    private Double right;

}
