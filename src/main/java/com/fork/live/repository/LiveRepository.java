package com.fork.live.repository;

import com.fork.live.old.Live;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LiveRepository extends MongoRepository<Live, Long> {

    List<Live> findAllByOrderByTimeDesc();

}
