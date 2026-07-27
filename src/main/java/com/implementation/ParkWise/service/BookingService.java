package com.implementation.ParkWise.service;

import com.implementation.ParkWise.dto.request.BookingRequest;
import com.implementation.ParkWise.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    public BookingResponse createBooking(BookingRequest request);
    public List<BookingResponse> getAllBookings();

    public void cancelBooking(Integer bookingId);

    public String completeBooking(Integer bookingId);
}
