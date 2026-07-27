package com.implementation.ParkWise.repository;

import com.implementation.ParkWise.entity.Booking;
import com.implementation.ParkWise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findByUser(User user);


}
