package com.banking.mlwithsandy.stockrecords.controller;

import com.banking.mlwithsandy.stockrecords.Service.PositionsService;
import com.banking.mlwithsandy.stockrecords.dto.PositionDto;
import com.banking.mlwithsandy.stockrecords.mapper.PositionMapperImpl;
import com.banking.mlwithsandy.stockrecords.model.Position;
import com.banking.mlwithsandy.stockrecords.model.StockRecordKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@RestController
@Slf4j
public class PositionsController {

  @Autowired private PositionsService positionsService;

  // Write a POST method to save a Position object to MongoDB using PositionService
  @PostMapping("/positions")
  public ResponseEntity<Object> savePosition(
          @RequestBody PositionDto positionDto, Principal principal) {
    if (Objects.isNull(positionDto)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Position object is null");
    }
    var position = PositionMapperImpl.INSTANCE.positionDtoToPosition(positionDto);

    try {
      var savedPosition = positionsService.save(position, principal);
      return ResponseEntity.status(HttpStatus.OK).body(savedPosition);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PostMapping("/positions/search")
  public ResponseEntity<Object> searchPositions(
      @RequestBody PositionDto searchCriteriaDto, Principal principal) {
    if (Objects.isNull(searchCriteriaDto)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Position object is null");
    }

    var searchCriteria = PositionMapperImpl.INSTANCE.positionDtoToPosition(searchCriteriaDto);

    try {
      var positionsList = positionsService.search(searchCriteria, principal);
      return ResponseEntity.status(HttpStatus.OK).body(positionsList);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  // Write a GET method with id as path variable and returns a Position object using PositionService
  @GetMapping("/positions/{id}")
  public ResponseEntity<Object> getPositionById(@PathVariable String id, Principal principal) {
    if (Objects.isNull(id)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Position id is null");
    }

    try {
      var position = positionsService.getById(id, principal);
      if (Objects.isNull(position)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Position not found");
      } else {
        return ResponseEntity.status(HttpStatus.OK).body(position);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @GetMapping("/positions")
  public ResponseEntity<Object> getPositionByAccountNo(
      @RequestParam String accountNo,
      @RequestParam(required = false) Long positionSequenceId,
      Principal principal) {
    if (accountNo.isBlank()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("accountNo should not be blank");
    }

    if (positionSequenceId != null && positionSequenceId <= 0) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("positionSequenceNo should not be <= 0");
    }

    var positionBuilder = Position.builder();
    var stockRecordKey = new StockRecordKey();
    stockRecordKey.setAccountNo(accountNo);
    positionBuilder.stockRecordKey(stockRecordKey);
    if (positionSequenceId != null) {
      positionBuilder.positionSequenceId(positionSequenceId);
    }

    var searchCriteria = positionBuilder.build();

    try {
      var positionsList = positionsService.search(searchCriteria, principal);
      return ResponseEntity.status(HttpStatus.OK).body(positionsList);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
}
