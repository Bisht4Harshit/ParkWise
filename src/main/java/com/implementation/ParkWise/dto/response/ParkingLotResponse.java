package com.implementation.ParkWise.dto.response;

import lombok.Data;

import java.time.LocalTime;
@Data
public class ParkingLotResponse {
    private String name;
    private String blockNo;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private Integer totalSlots;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Integer id;
}
