package com.fork.repository;

import com.fork.model.TwoOfThree;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TwoOfThreeRepository extends MongoRepository<TwoOfThree, Long> {
    
}
