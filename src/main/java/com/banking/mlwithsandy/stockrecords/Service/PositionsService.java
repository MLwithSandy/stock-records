package com.banking.mlwithsandy.stockrecords.Service;

import com.banking.mlwithsandy.stockrecords.model.*;
import com.banking.mlwithsandy.stockrecords.repository.PositionsRepository;
import com.banking.mlwithsandy.stockrecords.utility.SearchPredicate;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@Validated
@Transactional
public class PositionsService implements StockRecordsService<Position>{

    @Autowired
    private PositionsRepository positionsRepository;

    // write a method which takes Position object and saves it to PositionsRepository
    @Override
    public Position save(Position position, Principal principal) {
        validate(position);

        if (Objects.isNull(position)) {
            throw new IllegalArgumentException("Position cannot be null");
        }

        try {
            var matchingPosition = positionsRepository.findByStockRecordKey(position.getStockRecordKey());
            if (Objects.nonNull(matchingPosition)) {
                position.setId(matchingPosition.getId());
                position.setPositionSequenceId(matchingPosition.getPositionSequenceId());
            }  else {
                var latestPosition = positionsRepository.findTopByStockRecordKeyAccountNoOrderByIdDesc(position.getStockRecordKey().getAccountNo());

                if (Objects.nonNull(latestPosition)) {
                    position.setPositionSequenceId(latestPosition.getPositionSequenceId() + 1);
                } else {
                    position.setPositionSequenceId(1L);
                }
            }
        } catch (Exception e) {
            log.error("Error while getting position: " + e.getMessage());
            throw e;
        }
        position.setAudit(new Audit(principal));
        try {
            var savedPosition = positionsRepository.save(position);
            return savedPosition;
        } catch (Exception e) {
            log.error("Error while saving position: " + e.getMessage());
            throw e;
        }
    }

    // Write a method which takes an ID and returns a Position object from PositionsRepository
    @Override
    public Position getById(String id, Principal principal) {
        try {
            var position = positionsRepository.findById(id);
            return position.orElse(null);
        } catch (Exception e) {
            log.error("Error while getting position: " + e.getMessage());
            throw e;
        }
    }
    @Override
    public List<Position> search(Position searchCriteria, Principal principal) {
        var predicate = createSearchPredicate(searchCriteria);
        List<Position> positionsList = (List<Position>) positionsRepository.findAll(predicate);
        return positionsList;
    }

    private Predicate createSearchPredicate(Position searchCriteria) {
        QPosition qPosition = new QPosition("position");
        var qStockRecordKey = qPosition.stockRecordKey;

        var defaultPredicate = qPosition.id.isNotEmpty();

        var booleanExpressions = SearchPredicate.generateStockRecordsKeySearchPredicateEq(qStockRecordKey, searchCriteria.getStockRecordKey());
        booleanExpressions.add(Objects.isNull(searchCriteria.getPositionSequenceId())? null : qPosition.positionSequenceId.eq(searchCriteria.getPositionSequenceId()));
        booleanExpressions.add(Objects.isNull(searchCriteria.getQuantity())? null : qPosition.quantity.eq(searchCriteria.getQuantity()));
        booleanExpressions.add(Objects.isNull(searchCriteria.getUnitPriceTradeCurrency())? null : qPosition.unitPriceTradeCurrency.eq(searchCriteria.getUnitPriceTradeCurrency()));

        Predicate predicate =
                booleanExpressions.stream()
                        .filter(Objects::nonNull)
                        .reduce(defaultPredicate, BooleanExpression::and);

        log.info("Position search predicate: " + predicate);

        return predicate;
    }

    private void validate(Position position) throws IllegalArgumentException {
        if (Objects.isNull(position)) {
            throw new IllegalArgumentException("Position cannot be null");
        }

        if (position.getQuantity().compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than zero");
        }

        if (position.getUnitPriceTradeCurrency().compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("UnitPriceTradeCurrency cannot be less than zero");
        }
    }
}

