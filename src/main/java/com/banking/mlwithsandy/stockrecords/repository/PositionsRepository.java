package com.banking.mlwithsandy.stockrecords.repository;

import com.banking.mlwithsandy.stockrecords.model.Position;
import com.banking.mlwithsandy.stockrecords.model.StockRecordKey;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

@JaversSpringDataAuditable
public interface PositionsRepository extends MongoRepository <Position, String>, QuerydslPredicateExecutor<Position> {
    Position findTopByStockRecordKeyAccountNoOrderByIdDesc(String accountNo);
    Position findByStockRecordKey(StockRecordKey stockRecordKey);

}
