package com.banking.mlwithsandy.stockrecords.dto;

import com.banking.mlwithsandy.stockrecords.model.Booking;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class BookingRequestDto {
    private String requestId;
    private List<Booking> bookingsList;
}



