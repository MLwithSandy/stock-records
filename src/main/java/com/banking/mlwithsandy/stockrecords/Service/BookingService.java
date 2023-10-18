package com.banking.mlwithsandy.stockrecords.Service;

import com.banking.mlwithsandy.stockrecords.model.BookingRequest;
import com.banking.mlwithsandy.stockrecords.model.Movement;
import com.banking.mlwithsandy.stockrecords.model.Position;

import java.security.Principal;
import java.util.List;

public interface BookingService {
    public List<Position> book(List<Movement> movementsList, Principal principal);

    public BookingRequest save(BookingRequest bookingRequest, Principal principal);

    public BookingRequest getById(String id, Principal principal);
}
