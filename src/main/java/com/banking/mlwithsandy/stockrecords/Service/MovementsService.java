package com.banking.mlwithsandy.stockrecords.Service;

import com.banking.mlwithsandy.stockrecords.model.Audit;
import com.banking.mlwithsandy.stockrecords.model.Movement;
import com.banking.mlwithsandy.stockrecords.model.Position;
import com.banking.mlwithsandy.stockrecords.model.QMovement;
import com.banking.mlwithsandy.stockrecords.repository.MovementsRepository;
import com.banking.mlwithsandy.stockrecords.utility.SearchPredicate;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@Transactional
public class MovementsService implements StockRecordsService<Movement> {

  @Autowired
  private MovementsRepository movementsRepository;

  // write a method which takes Movement object and saves it to MovementsRepository
  @Override
  public Movement save(Movement movement, Principal principal) {
    validate(movement);

    var audit = new Audit(principal);
    movement.setAudit(audit);

    try {
      var savedmovement = movementsRepository.save(movement);
      return savedmovement;
    } catch (Exception e) {
      log.error("Error while saving movement: " + e.getMessage());
      throw e;
    }
  }

  public List<Movement> saveMovementsList(List<Movement> movementsList, Principal principal) {
    movementsList.forEach(movement -> {
      validate(movement);
    });

    var audit = new Audit(principal);

    movementsList.forEach(movement -> {
      movement.setAudit(audit);
    });

    try {
      var savedmovementsList = movementsRepository.saveAll(movementsList);
      return savedmovementsList;
    } catch (Exception e) {
      log.error("Error while saving movement: " + e.getMessage());
      throw e;
    }
  }

  // Write a method which takes an ID and returns a movement object from movementsRepository
  @Override
  public Movement getById(String id, Principal principal) {
    try {
      var movement = movementsRepository.findById(id);
      return movement.orElse(null);
    } catch (Exception e) {
      log.error("Error while getting movement: " + e.getMessage());
      throw e;
    }
  }

  @Override
  public List<Movement> search(Movement searchCriteria, Principal principal) {
    var predicate = createSearchPredicate(searchCriteria);
    List<Movement> movementList = (List<Movement>) movementsRepository.findAll(predicate);
    return movementList;
  }

  private Predicate createSearchPredicate(Movement searchCriteria) {
    QMovement qMovement = new QMovement("movement");
    var qStockRecordKey = qMovement.stockRecordKey;

    var defaultPredicate = qMovement.id.isNotEmpty();

    var booleanExpressions = SearchPredicate.generateStockRecordsKeySearchPredicateEq(qStockRecordKey, searchCriteria.getStockRecordKey());

    booleanExpressions.add(Objects.isNull(searchCriteria.getQuantity())? null : qMovement.quantity.eq(searchCriteria.getQuantity()));
    booleanExpressions.add(Objects.isNull(searchCriteria.getSettlementObligationId())? null : qMovement.settlementObligationId.eq(searchCriteria.getSettlementObligationId()));
    booleanExpressions.add(Objects.isNull(searchCriteria.getMovementType())? null : qMovement.movementType.eq(searchCriteria.getMovementType()));
    booleanExpressions.add(Objects.isNull(searchCriteria.getRemarks())? null : qMovement.remarks.eq(searchCriteria.getRemarks()));
    booleanExpressions.add(Objects.isNull(searchCriteria.getBookingRequestId())? null : qMovement.bookingRequestId.like(searchCriteria.getBookingRequestId()));
    booleanExpressions.add(Objects.isNull(searchCriteria.getLinkedPositionId())? null : qMovement.linkedPositionId.eq(searchCriteria.getLinkedPositionId()));
    booleanExpressions.add(Objects.isNull(searchCriteria.getBookingState())? null : qMovement.bookingState.eq(searchCriteria.getBookingState()));
    booleanExpressions.add(Objects.isNull(searchCriteria.getProcessStatus())? null : qMovement.processStatus.eq(searchCriteria.getProcessStatus()));
    booleanExpressions.add(Objects.isNull(searchCriteria.getProcessStatusReason())? null : qMovement.processStatusReason.eq(searchCriteria.getProcessStatusReason()));

    var qReversalDetails = qMovement.reversalDetails;

    if (Objects.nonNull(searchCriteria.getReversalDetails())) {
      var reversalDetails = searchCriteria.getReversalDetails();
      booleanExpressions.add(Objects.isNull(reversalDetails.getReversalDate())? null : qReversalDetails.reversalDate.eq(reversalDetails.getReversalDate()));
      booleanExpressions.add(Objects.isNull(reversalDetails.getReversalReason())? null : qReversalDetails.reversalReason.eq(reversalDetails.getReversalReason()));
      booleanExpressions.add(Objects.isNull(reversalDetails.getLinkedMovementId())? null : qReversalDetails.linkedMovementId.eq(reversalDetails.getLinkedMovementId()));
    }

    Predicate predicate =
        booleanExpressions.stream()
            .filter(Objects::nonNull)
            .reduce(defaultPredicate, BooleanExpression::and);

    log.info("Movement search predicate: " + predicate);

    return predicate;
  }

  private void validate(Movement movement) throws IllegalArgumentException {
    if (Objects.isNull(movement)) {
      throw new IllegalArgumentException("Position cannot be null");
    }

    if (movement.getQuantity().compareTo(new BigDecimal(0)) < 0) {
      throw new IllegalArgumentException("Quantity cannot be less than zero");
    }

    if (movement.getUnitPriceTradeCurrency().compareTo(new BigDecimal(0)) < 0) {
      throw new IllegalArgumentException("UnitPriceTradeCurrency cannot be less than zero");
    }
    if (movement.getTotalPriceTradeCurrency().compareTo(new BigDecimal(0)) < 0) {
      throw new IllegalArgumentException("TotalPriceTradeCurrency cannot be less than zero");
    }
  }
}
