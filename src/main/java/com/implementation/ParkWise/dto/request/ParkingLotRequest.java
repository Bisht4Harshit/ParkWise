package com.implementation.ParkWise.dto.request;

import lombok.Data;

import java.time.LocalTime;
@Data
public class ParkingLotRequest {
    private String name;
    private String blockNo;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private Integer totalSlots;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
