package com.fork.repository;

import com.fork.model.BookMaker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookMakerRepository extends MongoRepository<BookMaker, String> {

}
