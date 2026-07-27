package com.implementation.ParkWise.entity;

import com.implementation.ParkWise.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String bookingNo;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id",nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "parking_slot_id",nullable = false)
    private ParkingSlot parkingSlot;


    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(unique = true)
    private String qrCode;


    private LocalDateTime bookingStartTime;
    private LocalDateTime bookingEndTime;

    private LocalDateTime actualEntryTime;
    private LocalDateTime actualExitTime;

}
