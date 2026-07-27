package com.implementation.ParkWise.dto.request;

import com.implementation.ParkWise.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleRequest {
    private String model;
    private String brand;
    private String color;
    private String registrationNo;
    private VehicleType vehicleType;
}
