package com.implementation.ParkWise.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequest {

    private Integer vehicleId;
    private Integer parkingSlotId;
    private LocalDateTime bookingStartTime;
    private LocalDateTime bookingEndTime;

}
