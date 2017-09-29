package com.fork.repository;

import com.fork.model.FullMatch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<FullMatch, Long> {

}
