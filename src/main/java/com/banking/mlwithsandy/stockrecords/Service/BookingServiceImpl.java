package com.banking.mlwithsandy.stockrecords.Service;

import com.banking.mlwithsandy.stockrecords.mapper.MovementMapperImpl;
import com.banking.mlwithsandy.stockrecords.mapper.PositionMapperImpl;
import com.banking.mlwithsandy.stockrecords.model.*;
import com.banking.mlwithsandy.stockrecords.repository.BookingsRequestRepository;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.min;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

  @Autowired private PositionsService positionsService;

  @Autowired private MovementsService movementsService;

  @Autowired private BookingsRequestRepository bookingRequestRepository;


  @Transactional
  @Override
  public BookingRequest save(BookingRequest bookingRequest, Principal principal) {

    if (Objects.isNull(bookingRequest)) {
      throw new ValidationException("BookingRequest object is null, should not be null");
    }

    bookingRequest.setAudit(new Audit(principal));
    bookingRequest.setProcessStatus(ProcessStatus.INITIAL);

    try {
      var savedBookingRequest = bookingRequestRepository.save(bookingRequest);

      savedBookingRequest = processBookingRequest(savedBookingRequest, principal);

      log.info(String.format("booking request processed for id: %d, uniqueBookingRequestId: %s, processStatus: %s",
              savedBookingRequest.getId(), savedBookingRequest.getUniqueBookingRequestId(), savedBookingRequest.getProcessStatus()));

      return savedBookingRequest;
    } catch (Exception e) {
      log.error("Error while saving position: " + e.getMessage());
      throw e;
    }
  }

  @Override
  public BookingRequest getById(String id, Principal principal) {
    try {
      var bookingRequest = bookingRequestRepository.findById(id);
      return bookingRequest.orElse(null);
    } catch (Exception e) {
      log.error("Error while getting bookingRequest: " + e.getMessage());
      throw e;
    }
  }

  @Transactional
  @Override
  public List<Position> book(List<Movement> movementsList, Principal principal) {
    var positionList =
        movementsList.parallelStream()
            .map(PositionMapperImpl.INSTANCE::movement2Position)
            .filter(Objects::nonNull)
            .toList();

    // @TODO - return updated position list
    positionList.forEach(
        x -> {
          var savedQuantity = x.getQuantity();
          x.setQuantity(null);
          // @TODO - check for more than one position in the result
          var existingPositionList = positionsService.search(x, principal);

          x.setQuantity(savedQuantity);
          if (existingPositionList.isEmpty()) {
            positionsService.save(x, principal);
          } else {
            var existingPosition = existingPositionList.get(0);
            var quantity = x.getQuantity().add(existingPosition.getQuantity());
            x.setQuantity(quantity);
            // @TODO - check for negative quantity
            var result = positionsService.save(x, principal);
            log.info("Result: {}", result);
          }
        });
    return positionList;
  }

  @Scheduled(initialDelay = 1 * 60 * 1000, fixedDelay = 1 * 60 * 1000)
  public void retryBookingRequestProcessing() {
    log.info("Retrying booking request processing initiated");
    try {
      var bookingRequestList =
          bookingRequestRepository.findByProcessStatusOrderById(ProcessStatus.ERROR.toString());
      bookingRequestList.forEach(
              bookingRequest -> this.processBookingRequest(bookingRequest, null));

    } catch (Exception e) {
      log.error(String.format("Error while retrying booking request,  errorMessage: %s", e.getMessage()));
    }
    log.info("Retrying booking request processing completed");
  }

  @Transactional
  public BookingRequest processBookingRequest(BookingRequest bookingRequest, Principal principal) {

    BookingRequest updatedBookingRequest = null;
    log.info("Processing booking request: {}", bookingRequest.getId());

    try {
      var movementsList = createMovementsFromBookingRequest(bookingRequest, principal);
      bookingRequest.getAudit().setUpdateDateTime(LocalDateTime.now());
      bookingRequest.setProcessStatus(ProcessStatus.SUCCESS);
      bookingRequest.setProcessStatusReason("");
      updatedBookingRequest = bookingRequestRepository.save(bookingRequest);
    } catch (Exception e) {
      log.error(
              String.format(
                      "booking request: %d updated for error message: %s",
                      bookingRequest.getId(), e.getMessage()));
      bookingRequest.getAudit().setUpdateDateTime(LocalDateTime.now());
      bookingRequest.setProcessStatus(ProcessStatus.ERROR);
      bookingRequest.setProcessStatusReason(e.getMessage().substring(0, min(e.getMessage().length(), 255)));
      updatedBookingRequest = bookingRequestRepository.save(bookingRequest);
    }

    return updatedBookingRequest;
  }

  private List<Movement> createMovementsFromBookingRequest(BookingRequest bookingRequest, Principal principal) {
    List<Movement> movementsList = Collections.emptyList();

    if (Objects.nonNull(bookingRequest)
            && !bookingRequest.getProcessStatus().equals(ProcessStatus.SUCCESS)) {
      try {
        movementsList = MovementMapperImpl.INSTANCE.bookingRequestToMovementsList(bookingRequest);
        movementsList.forEach(movement -> movement.setAudit(new Audit(principal)));
        movementsService.saveMovementsList(movementsList, principal);
      } catch (Exception e) {
        log.error(
                String.format(
                        "Error while creating movements from booking request: %d, error message: %s",
                        bookingRequest.getId(), e.getMessage()));
        throw e;
      }
    } else if (Objects.isNull(bookingRequest)) {
      throw new ValidationException("BookingRequest is null, it should not be null");
    } else if (bookingRequest.getProcessStatus().equals(ProcessStatus.SUCCESS)) {
      throw new ValidationException("BookingRequest is already processed");
    }
    return movementsList;
  }

  private Pair<BigDecimal, BigDecimal> calculateAveragePurchasePrice(BigDecimal quantity, BigDecimal purchasePrice, BigDecimal newQuantity, BigDecimal newPurchasePrice) {
    if (newQuantity.compareTo(new BigDecimal(0)) < 0) {
      return Pair.of(quantity, purchasePrice);
    }

    var totalQuantity = quantity.add(newQuantity);
    var totalPurchasePrice = quantity.multiply(purchasePrice)
            .add(newQuantity.multiply(newPurchasePrice));
    var averagePurchasePrice = totalPurchasePrice.divide(totalQuantity);
    return Pair.of(totalQuantity, averagePurchasePrice);
  }
}
