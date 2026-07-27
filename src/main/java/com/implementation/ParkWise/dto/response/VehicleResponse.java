package com.implementation.ParkWise.dto.response;

import com.implementation.ParkWise.enums.VehicleType;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VehicleResponse {

    private Integer id;

    private String model;

    private String brand;

    private String color;

    private String registrationNo;

    private VehicleType vehicleType;

    private Integer userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
