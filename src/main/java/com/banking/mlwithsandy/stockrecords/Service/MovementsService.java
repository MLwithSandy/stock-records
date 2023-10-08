package com.banking.mlwithsandy.stockrecords.Service;

import com.banking.mlwithsandy.stockrecords.model.Movement;
import com.banking.mlwithsandy.stockrecords.repository.MovementsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class MovementsService implements StockRecordsService<Movement> {

  @Autowired private MovementsRepository movementsRepository;

  // write a method which takes Movement object and saves it to MovementsRepository
  @Override
  public Movement save(Movement movement) {
    try {
      movement.setId(movementsRepository.count() + 1);
    } catch (Exception e) {
      movement.setId(1L);
    }
    try {
      var savedmovement = movementsRepository.insert(movement);
      return savedmovement;
    } catch (Exception e) {
      log.error("Error while saving movement: " + e.getMessage());
      throw e;
    }
  }

  // Write a method which takes an ID and returns a movement object from movementsRepository
  @Override
  public Movement getById(Long id) {
    try {
      var movement = movementsRepository.findById(id);
      return movement.orElse(null);
    } catch (Exception e) {
      log.error("Error while getting movement: " + e.getMessage());
      throw e;
    }
  }
}
