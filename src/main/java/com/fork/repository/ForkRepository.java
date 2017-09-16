package com.fork.repository;

import com.fork.model.Fork;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ForkRepository extends MongoRepository<Fork, Long> {

}
