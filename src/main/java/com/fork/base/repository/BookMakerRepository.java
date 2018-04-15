package com.fork.base.repository;

import com.fork.base.model.BookMaker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookMakerRepository extends MongoRepository<BookMaker, String> {

    List<BookMaker> findBySportType(String sportType);
    BookMaker findOneByNameAndSportType(String name, String sportType);
    void deleteByNameAndSportType(String name, String sportType);

}
