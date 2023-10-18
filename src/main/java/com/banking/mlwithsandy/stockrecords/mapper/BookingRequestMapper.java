package com.banking.mlwithsandy.stockrecords.mapper;

import com.banking.mlwithsandy.stockrecords.dto.BookingRequestDto;
import com.banking.mlwithsandy.stockrecords.model.BookingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingRequestMapper {
    BookingRequestMapper INSTANCE = Mappers.getMapper(BookingRequestMapper.class);

    BookingRequestDto bookingRequestToBookingRequestDto(BookingRequest bookingRequest);
    BookingRequest bookingRequestDtoToBookingRequest(BookingRequestDto bookingRequestDto);
}
