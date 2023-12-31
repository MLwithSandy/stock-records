package com.banking.mlwithsandy.stockrecords.controller;

import com.banking.mlwithsandy.stockrecords.Service.MovementsService;
import com.banking.mlwithsandy.stockrecords.model.Movement;

import java.security.Principal;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MovementsController {

    @Autowired
    private MovementsService movementsService;

    // Write a POST method to save a Movement object to MongoDB using MovementService
    @PostMapping("/movements")
    public ResponseEntity<Object> saveMovement(@RequestBody Movement movement, Principal principal) {
        if (Objects.isNull(movement)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movement object is null");
        }

        try {
            var savedMovement = movementsService.save(movement, principal);
            return ResponseEntity.status(HttpStatus.OK).body(savedMovement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/movements/search")
    public ResponseEntity<Object> searchMovements(@RequestBody Movement searchCriteria, Principal principal) {
        if (Objects.isNull(searchCriteria)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movement object is null");
        }

        try {
            var movementsList = movementsService.search(searchCriteria, principal);
            return ResponseEntity.status(HttpStatus.OK).body(movementsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Write a GET method with id as path variable and returns a Movement object using MovementService
    @GetMapping("/movements/{id}")
    public ResponseEntity<Object> getMovementById(@PathVariable String id, Principal principal) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movement id is null");
        }

        try {
            var movement = movementsService.getById(id, principal);
            log.info("Movement: {}", movement);
            if (Objects.isNull(movement)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movement not found");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(movement);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

