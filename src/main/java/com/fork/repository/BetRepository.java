package com.fork.repository;

import com.fork.model.Bet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BetRepository extends MongoRepository<Bet, Long> {

}
