package com.fork.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "BookMaker")
public class BookMaker {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String sportType;

    @Getter @Setter
    private List<Match> matches = new ArrayList<>();

    public BookMaker() {
    }

    public BookMaker(String name, String type) {
        this.name = name;
        this.sportType = type;
    }
}
