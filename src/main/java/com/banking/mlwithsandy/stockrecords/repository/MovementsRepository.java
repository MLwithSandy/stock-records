package com.banking.mlwithsandy.stockrecords.repository;

import com.banking.mlwithsandy.stockrecords.model.Movement;
import com.banking.mlwithsandy.stockrecords.model.Position;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovementsRepository extends MongoRepository <Movement, Long> {

}
