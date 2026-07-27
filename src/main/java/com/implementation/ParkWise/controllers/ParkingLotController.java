package com.implementation.ParkWise.controllers;

import com.implementation.ParkWise.dto.request.ParkingLotRequest;
import com.implementation.ParkWise.dto.response.ParkingLotResponse;
import com.implementation.ParkWise.service.ParkingLotServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkingLot")
public class ParkingLotController {

    private final ParkingLotServiceImpl parkingLotService;

    public ParkingLotController(ParkingLotServiceImpl parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping("/add")
    public ResponseEntity<ParkingLotResponse> addParkingLot(
            @RequestBody ParkingLotRequest request)
    {
         return ResponseEntity.ok(parkingLotService.addParkingLot(request));
    }

    @GetMapping("/my")
    public  ResponseEntity <List<ParkingLotResponse>> getMyParkingLots()
    {
        return ResponseEntity.ok(parkingLotService.getMyParkingLots());
    }

    @PutMapping("/{parkingLotId}")
    public ResponseEntity<ParkingLotResponse> updateParkingLot(
            @PathVariable Integer parkingLotId,
            @RequestBody ParkingLotRequest request) {
        return ResponseEntity.ok(parkingLotService.updateParkingLot(parkingLotId,request));
    }

    @DeleteMapping("/{parkingLotId}")
    public ResponseEntity<String> deleteParkingLot(
            @PathVariable Integer parkingLotId) {
       parkingLotService.deleteParkingLot(parkingLotId);
       return ResponseEntity.ok("Parking lot  deleted successfully");
    }

}
