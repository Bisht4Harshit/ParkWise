package com.implementation.ParkWise.entity;

import com.implementation.ParkWise.enums.SlotType;
import com.implementation.ParkWise.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String slotNo;


    @Enumerated(EnumType.STRING)
    private SlotType slotType;


    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "parking_Lot_Id",nullable = false)
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingSlot")
    private List<Booking> bookings;

}