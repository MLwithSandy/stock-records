package com.banking.mlwithsandy.stockrecords.controller;

import com.banking.mlwithsandy.stockrecords.Service.BookingService;
import com.banking.mlwithsandy.stockrecords.Service.MovementsService;
import com.banking.mlwithsandy.stockrecords.model.BookingRequest;
import com.banking.mlwithsandy.stockrecords.model.Movement;

import java.security.Principal;
import java.util.Objects;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BookingsController {

    @Autowired
    private BookingService bookingService;

    // Write a POST method to save a Movement object to MongoDB using MovementService
    @PostMapping("/bookings")
    public ResponseEntity<Object> saveBookingRequest(@RequestBody BookingRequest bookingRequest, Principal principal) {
        if (Objects.isNull(bookingRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BookingRequest object is null");
        }

        try {
            var savedBookingRequest = bookingService.save(bookingRequest, principal);
            return ResponseEntity.status(HttpStatus.OK).body(savedBookingRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Write a GET method with id as path variable and returns a Movement object using MovementService
    @GetMapping("/bookings/{id}")
    public ResponseEntity<Object> getBookingRequestById(@PathVariable String id, Principal principal) {
        if (Objects.isNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movement id is null");
        }

        try {
            var movement = bookingService.getById(id, principal);
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

