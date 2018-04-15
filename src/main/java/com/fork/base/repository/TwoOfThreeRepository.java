package com.fork.base.repository;

import com.fork.base.model.TwoOfThree;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TwoOfThreeRepository extends MongoRepository<TwoOfThree, Long> {

    List<TwoOfThree> findAllByOrderBySumPercentBet();
    
}
