package com.fork.base.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "BookMaker")
public class BookMaker {

    private String name;
    private String sportType;

    private List<Match> matches = new ArrayList<>();

    public BookMaker() {
    }

    public BookMaker(String name, String type) {
        this.name = name;
        this.sportType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
