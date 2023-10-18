package com.banking.mlwithsandy.stockrecords.repository;

import com.banking.mlwithsandy.stockrecords.model.BookingRequest;
import com.banking.mlwithsandy.stockrecords.model.Movement;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
@JaversSpringDataAuditable
public interface BookingsRequestRepository extends MongoRepository <BookingRequest, String>{
    public BookingRequest findByUniqueBookingRequestId(String uniqueBookingRequestId);

    public List<BookingRequest> findByProcessStatusOrderById(String processStatus);
}
