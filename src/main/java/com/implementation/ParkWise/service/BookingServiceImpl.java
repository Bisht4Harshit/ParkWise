package com.implementation.ParkWise.service;

import com.implementation.ParkWise.dto.request.BookingRequest;
import com.implementation.ParkWise.dto.response.BookingResponse;
import com.implementation.ParkWise.entity.Booking;
import com.implementation.ParkWise.entity.ParkingSlot;
import com.implementation.ParkWise.entity.User;
import com.implementation.ParkWise.entity.Vehicle;
import com.implementation.ParkWise.enums.BookingStatus;
import com.implementation.ParkWise.enums.Status;
import com.implementation.ParkWise.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingServiceImpl implements BookingService{
    private final UserRepository userRepo;
    private final VehicleRepository vehicleRepo;
    private final BookingRepository bookingRepo;
    private final ParkingSlotRepository parkingSlotRepo;
    private final ParkingLotRepository parkingLotRepo;

    BookingServiceImpl(UserRepository userRepo, VehicleRepository vehicleRepo, BookingRepository bookingRepo, ParkingSlotRepository parkingSlotRepo, ParkingLotRepository parkingLotRepo) {
        this.userRepo = userRepo;
        this.vehicleRepo = vehicleRepo;
        this.bookingRepo = bookingRepo;
        this.parkingSlotRepo = parkingSlotRepo;
        this.parkingLotRepo = parkingLotRepo;
    }
    @Override
    public BookingResponse createBooking(BookingRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepo.findById(request.getVehicleId())
                .orElseThrow(()->new RuntimeException("Vehicle not found"));
        if(!vehicle.getUser().getId().equals(user.getId())){
            throw new RuntimeException("This vehicle does not belong to u");
        }
        ParkingSlot parkingSlot = parkingSlotRepo.findById(request.getParkingSlotId())
                .orElseThrow(()-> new RuntimeException("Parking slot is not found"));
        if(parkingSlot.getStatus() != Status.AVAILABLE) {
            throw new RuntimeException("Parking slot is already occupied");
        }

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setVehicle(vehicle);
        booking.setParkingSlot(parkingSlot);
        booking.setBookingStartTime(request.getBookingStartTime());
        booking.setBookingEndTime(request.getBookingEndTime());
        booking.setStatus(BookingStatus.BOOKED);
        booking.setBookingNo("BK"+ System.currentTimeMillis());
        booking.setAmount(BigDecimal.valueOf(100.0));
        Booking savedBooking = bookingRepo.save(booking);

        parkingSlot.setStatus(Status.OCCUPIED);

        parkingSlotRepo.save(parkingSlot);

        BookingResponse response = new BookingResponse();

        response.setId(savedBooking.getId());
        response.setBookingNo(savedBooking.getBookingNo());
        response.setStatus(savedBooking.getStatus());
        response.setBookingStartTime(savedBooking.getBookingStartTime());
        response.setBookingEndTime(savedBooking.getBookingEndTime());
        response.setSlotNo(savedBooking.getParkingSlot().getSlotNo());
        response.setVehicleRegistrationNo(savedBooking.getVehicle().getRegistrationNo());
        response.setParkingLotName(savedBooking.getParkingSlot().getParkingLot().getName());

        return response;
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));
        List<Booking> bookings =
                bookingRepo.findByUser(user);
        List<BookingResponse> response = new ArrayList<>();
        for(Booking booking: bookings) {
            BookingResponse dto = new BookingResponse();

            dto.setId(booking.getId());
            dto.setBookingNo(booking.getBookingNo());
            dto.setStatus(booking.getStatus());
            dto.setAmount(booking.getAmount());
            dto.setBookingStartTime(booking.getBookingStartTime());
            dto.setBookingEndTime(booking.getBookingEndTime());

            dto.setSlotNo(booking.getParkingSlot().getSlotNo());

            dto.setParkingLotName(booking.getParkingSlot()
                                 .getParkingLot()
                                 .getName()
            );
            dto.setVehicleRegistrationNo(booking.getVehicle()
                    .getRegistrationNo()
            );
           response.add(dto);

        }
        return response;
    }

    @Override
    public void cancelBooking(Integer bookingId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(()->new RuntimeException("Booking not found"));
        if(!booking.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You cannot cancel someone else's booking");
        }
        if(booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        ParkingSlot slot = booking.getParkingSlot();
        slot.setStatus(Status.AVAILABLE);
        parkingSlotRepo.save(slot);
        bookingRepo.save(booking);

    }

    @Override
    public String completeBooking(Integer bookingId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(()->new RuntimeException("Booking not found"));
        if(!booking.getUser().getId().equals(user.getId())){
            throw new RuntimeException("You cannot complete someone else's booking.");
        }
        if(booking.getStatus() == BookingStatus.CANCELLED){
            throw new RuntimeException("Cancelled bookings cannot be completed");
        }
        if(booking.getStatus() == BookingStatus.COMPLETED) {
            throw new RuntimeException("Booking is already completed");
        }
        booking.setActualExitTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.COMPLETED);
        ParkingSlot slot = booking.getParkingSlot();
        slot.setStatus(Status.AVAILABLE);
        parkingSlotRepo.save(slot);
        bookingRepo.save(booking);
        return "Booking completed successfully";
    }


}


