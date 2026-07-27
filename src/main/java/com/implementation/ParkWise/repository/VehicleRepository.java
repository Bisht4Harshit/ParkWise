package com.implementation.ParkWise.repository;

import com.implementation.ParkWise.entity.User;
import com.implementation.ParkWise.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    Integer id(Integer id);

    List<Vehicle> findByUser(User user);
}
