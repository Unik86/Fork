package com.fork.base.repository;

import com.fork.base.model.Fork;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ForkRepository extends MongoRepository<Fork, Long> {

    List<Fork> findBySportTypeOrderByRate(String sportType);
    List<Fork> findAllByOrderByForkBet();

    void deleteBySportType(String sportType);

}
