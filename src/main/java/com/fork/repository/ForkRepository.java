package com.fork.repository;

import com.fork.model.Fork;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ForkRepository extends MongoRepository<Fork, Long> {

    List<Fork> findAllByOrderByRate();

}
