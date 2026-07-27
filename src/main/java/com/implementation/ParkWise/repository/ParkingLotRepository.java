package com.implementation.ParkWise.repository;

import com.implementation.ParkWise.entity.ParkingLot;
import com.implementation.ParkWise.entity.User;
import com.implementation.ParkWise.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot,Integer> {

    List<ParkingLot> findByOwner(User owner);
}
