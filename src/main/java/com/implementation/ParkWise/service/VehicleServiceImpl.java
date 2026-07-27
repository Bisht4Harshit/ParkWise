package com.implementation.ParkWise.service;

import com.implementation.ParkWise.dto.request.VehicleRequest;
import com.implementation.ParkWise.dto.response.VehicleResponse;
import com.implementation.ParkWise.entity.User;
import com.implementation.ParkWise.entity.Vehicle;
import com.implementation.ParkWise.repository.UserRepository;
import com.implementation.ParkWise.repository.VehicleRepository;
import jakarta.persistence.criteria.CollectionJoin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepo;
    private final UserRepository  userRepo;

    public VehicleServiceImpl(VehicleRepository vehicleRepo, UserRepository userRepo) {
        this.vehicleRepo = vehicleRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<VehicleResponse> getAllVehicles() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        List<Vehicle> vehicles =
                vehicleRepo.findByUser(user);
        List<VehicleResponse> response = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {

            VehicleResponse dto = new VehicleResponse();

            dto.setId(vehicle.getId());
            dto.setModel(vehicle.getModel());
            dto.setBrand(vehicle.getBrand());
            dto.setColor(vehicle.getColor());
            dto.setRegistrationNo(vehicle.getRegistrationNo());
            dto.setVehicleType(vehicle.getVehicleType());

            response.add(dto);

        }
        return response;
    }

    @Override
    public Vehicle getVehicle(int id) {
        return vehicleRepo.findById(id).orElse(null);
    }

    @Override
    public VehicleResponse addVehicle(VehicleRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vehicle vehicle = new Vehicle();

        vehicle.setModel(request.getModel());
        vehicle.setBrand(request.getBrand());
        vehicle.setColor(request.getColor());
        vehicle.setRegistrationNo(request.getRegistrationNo());
        vehicle.setVehicleType(request.getVehicleType());

        vehicle.setUser(user);

        vehicleRepo.save(vehicle);

        Vehicle savedVehicle = vehicleRepo.save(vehicle);

        VehicleResponse response = new VehicleResponse();

        response.setId(savedVehicle.getId());
        response.setModel(savedVehicle.getModel());
        response.setBrand(savedVehicle.getBrand());
        response.setColor(savedVehicle.getColor());
        response.setRegistrationNo(savedVehicle.getRegistrationNo());
        response.setVehicleType(savedVehicle.getVehicleType());
        response.setUserId(savedVehicle.getUser().getId());

        return response;
    }

    @Override
    public VehicleResponse updateVehicle(Integer vehicleId, VehicleRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));
        if (!vehicle.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to update this vehicle");
        }

        vehicle.setModel(request.getModel());
        vehicle.setBrand(request.getBrand());
        vehicle.setColor(request.getColor());
        vehicle.setRegistrationNo(request.getRegistrationNo());
        vehicle.setVehicleType(request.getVehicleType());

        Vehicle updatedVehicle = vehicleRepo.save(vehicle);
        VehicleResponse response = new VehicleResponse();

        response.setId(updatedVehicle.getId());
        response.setModel(updatedVehicle.getModel());
        response.setBrand(updatedVehicle.getBrand());
        response.setColor(updatedVehicle.getColor());
        response.setRegistrationNo(updatedVehicle.getRegistrationNo());
        response.setVehicleType(updatedVehicle.getVehicleType());
        response.setUserId(updatedVehicle.getUser().getId());

        response.setCreatedAt(updatedVehicle.getCreatedAt());
        response.setUpdatedAt(updatedVehicle.getUpdatedAt());
        return response;
    }

    @Override
    public void deleteVehicle(Integer vehicleId) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));
        if (!vehicle.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this vehicle");
        }
        vehicleRepo.delete(vehicle);

    }


}
