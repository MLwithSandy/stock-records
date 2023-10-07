package com.banking.mlwithsandy.stockrecords.repository;

import com.banking.mlwithsandy.stockrecords.model.Position;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PositionsRepository extends MongoRepository <Position, Long> {

}
