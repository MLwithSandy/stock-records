package com.banking.mlwithsandy.stockrecords.model;

public enum BookingState {
    BOOKING_READY ("booking_ready"),
    BOOKED("booked");

    private final String bookingState;

    BookingState(String bookingState) {
        this.bookingState = bookingState.toUpperCase();
    }

    public String getBookingState() {
        return bookingState;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
