package com.banking.mlwithsandy.stockrecords.mapper;

import com.banking.mlwithsandy.stockrecords.dto.MovementDto;
import com.banking.mlwithsandy.stockrecords.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    MovementDto movementToMovementDto(Movement movement);
    Movement movementDtoToMovement(MovementDto movementDto);

    @BookingToMovementMapper
    default Movement bookingToMovement(Booking booking) {
        if (Objects.isNull(booking)) {
            return null;
        } else {
            var movementBuilder = Movement.builder();
            movementBuilder.stockRecordKey(booking.getStockRecordKey());
            movementBuilder.quantity(booking.getQuantity());
            movementBuilder.movementType(booking.getMovementType());
            movementBuilder.remarks(booking.getRemarks());
            movementBuilder.reversalDetails(booking.getReversalDetails());
            movementBuilder.unitPriceTradeCurrency(booking.getUnitPriceTradeCurrency());
            movementBuilder.totalPriceTradeCurrency(booking.getTotalPriceTradeCurrency());
            movementBuilder.settlementObligationId(booking.getSettlementObligationId());
            return movementBuilder.build();
        }
    }

    @BookingRequestToMovementsListMapper
    default List<Movement> bookingRequestToMovementsList(BookingRequest bookingRequest) {

        // default audit
        Audit audit = new Audit();

        if (Objects.isNull(bookingRequest)) {
            return null;
        } else {
            return bookingRequest.getBookingsList().stream()
                    .map(MovementMapperImpl.INSTANCE::bookingToMovement)
                    .map(
                            x -> {
                                x.setAudit(audit);
                                x.setProcessStatus(ProcessStatus.INITIAL);
                                x.setBookingState(BookingState.BOOKING_READY);
                                x.setBookingRequestId(bookingRequest.getId());
                                return x;
                            })
                    .filter(Objects::nonNull)
                    .toList();
        }
    }
}
