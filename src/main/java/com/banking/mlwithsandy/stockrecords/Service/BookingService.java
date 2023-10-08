package com.banking.mlwithsandy.stockrecords.Service;

import com.banking.mlwithsandy.stockrecords.model.Movement;
import com.banking.mlwithsandy.stockrecords.model.Position;

import java.util.List;

public interface BookingService {
    public List<Position> book(List<Movement> movementsList);
}
