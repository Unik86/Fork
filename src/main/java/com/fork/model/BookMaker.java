package com.fork.model;

import com.fork.model.enums.SportTypes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "BookMaker")
public class BookMaker {

    @Id
    @Getter @Setter
    private String name;
    @Getter @Setter
    private SportTypes sportType;
    @Getter @Setter
    private List<Match> matches = new ArrayList<>();

    public BookMaker() {

    }

    public BookMaker(String name) {
        this.name = name;
    }
}
