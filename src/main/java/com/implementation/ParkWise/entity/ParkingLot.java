package com.implementation.ParkWise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

   @OneToMany(mappedBy = "parkingLot")
   private List<ParkingSlot> parkingSlots;

   @Column(nullable = false)
   private String name;

   private String blockNo;
   private String street;
   @Column(nullable = false)
   private String city;
   private String state;
   private String pincode;
   @Column(nullable = false)
   private Integer totalSlots;

   private LocalTime openingTime;
   private LocalTime closingTime;

}
