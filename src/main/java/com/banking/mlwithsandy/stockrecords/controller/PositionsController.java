package com.banking.mlwithsandy.stockrecords.controller;

import com.banking.mlwithsandy.stockrecords.Service.PositionsService;
import com.banking.mlwithsandy.stockrecords.model.AssetClassSpecificAttributes;
import com.banking.mlwithsandy.stockrecords.model.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@Slf4j
public class PositionsController {

    @Autowired
    private PositionsService positionsService;

    // Write a POST method to save a Position object to MongoDB using PositionService
    @PostMapping("/positions")
    public ResponseEntity<Object> savePosition(@RequestBody Position position) {
        if (Objects.isNull(position)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Position object is null");
        }

        try {
            var savedPosition = positionsService.save(position);
            return ResponseEntity.status(HttpStatus.OK).body(savedPosition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Write a GET method with id as path variable and returns a Position object using PositionService
    @GetMapping("/positions/{id}")
    public ResponseEntity<Object> getPositionById(@PathVariable Long id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Position id is null");
        }

        try {
            var position = positionsService.getById(id);
            if (Objects.isNull(position)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Position not found");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(position);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

