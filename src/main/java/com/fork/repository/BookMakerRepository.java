package com.fork.repository;

import com.fork.model.BookMaker;
import com.fork.model.Fork;
import com.fork.model.enums.SportTypes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookMakerRepository extends MongoRepository<BookMaker, String> {

    List<BookMaker> findBySportType(SportTypes sportType);
    BookMaker findOneByNameAndSportType(String name, SportTypes sportType);

}
