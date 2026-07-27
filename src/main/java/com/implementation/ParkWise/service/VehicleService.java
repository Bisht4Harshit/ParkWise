package com.implementation.ParkWise.service;

import com.implementation.ParkWise.dto.request.VehicleRequest;
import com.implementation.ParkWise.dto.response.VehicleResponse;
import com.implementation.ParkWise.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    public List<VehicleResponse> getAllVehicles();

    public Vehicle getVehicle(int id);

    public VehicleResponse addVehicle(VehicleRequest request);

    public VehicleResponse updateVehicle(Integer vehicleId, VehicleRequest request);

    public void deleteVehicle(Integer vehicleId);
}
