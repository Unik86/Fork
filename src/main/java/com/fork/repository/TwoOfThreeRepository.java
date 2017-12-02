package com.fork.repository;

import com.fork.model.TwoOfThree;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TwoOfThreeRepository extends MongoRepository<TwoOfThree, Long> {

    List<TwoOfThree> findAllByOrderBySumPercentBet();
    
}
