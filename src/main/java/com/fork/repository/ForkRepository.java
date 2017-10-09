package com.fork.repository;

import com.fork.model.Fork;
import com.fork.model.enums.SportTypes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ForkRepository extends MongoRepository<Fork, Long> {

    List<Fork> findBySportTypeOrderByRate(String sportType);
    void deleteBySportType(String sportType);

}
