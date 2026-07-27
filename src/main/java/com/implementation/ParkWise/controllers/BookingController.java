package com.implementation.ParkWise.controllers;


import com.implementation.ParkWise.dto.request.BookingRequest;
import com.implementation.ParkWise.dto.response.BookingResponse;
import com.implementation.ParkWise.service.BookingService;
import com.implementation.ParkWise.service.BookingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingServiceImpl bookingService;

    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(
            @RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    @GetMapping("/my")
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(
            @PathVariable Integer bookingId) {
        bookingService.cancelBooking(bookingId);

        return ResponseEntity.ok("Booking cancelled successfully");

    }

    @PutMapping("/complete/{bookingId}")
    public ResponseEntity<String> completeBooking(
            @PathVariable Integer bookingId) {
        return ResponseEntity.ok(bookingService.completeBooking(bookingId));
    }

}
