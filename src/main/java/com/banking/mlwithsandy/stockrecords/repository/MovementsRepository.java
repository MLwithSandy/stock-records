package com.banking.mlwithsandy.stockrecords.repository;

import com.banking.mlwithsandy.stockrecords.model.Movement;
import com.banking.mlwithsandy.stockrecords.model.Position;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

@JaversSpringDataAuditable
public interface MovementsRepository extends MongoRepository <Movement, String>, QuerydslPredicateExecutor<Movement> {
}
