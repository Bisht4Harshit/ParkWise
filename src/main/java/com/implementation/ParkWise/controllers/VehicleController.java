package com.implementation.ParkWise.controllers;

import com.implementation.ParkWise.dto.request.VehicleRequest;
import com.implementation.ParkWise.dto.response.VehicleResponse;
import com.implementation.ParkWise.entity.Vehicle;
import com.implementation.ParkWise.service.VehicleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {


    private final VehicleServiceImpl vehicleServiceImpl;

    public VehicleController(VehicleServiceImpl vehicleServiceImpl) {
        this.vehicleServiceImpl = vehicleServiceImpl;
    }


    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles(){
        return new ResponseEntity<>(vehicleServiceImpl.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable int id){
        Vehicle vehicle = vehicleServiceImpl.getVehicle(id);
      if(vehicle!=null)
      {
          return new ResponseEntity<>(vehicle,HttpStatus.OK);
      }
      else
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody VehicleRequest vehicleRequest){

        return  ResponseEntity.ok(vehicleServiceImpl.addVehicle(vehicleRequest));

    }
    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponse> updateVehicle(
            @PathVariable Integer vehicleId,
            @RequestBody VehicleRequest request) {

        return ResponseEntity.ok(
                vehicleServiceImpl.updateVehicle(vehicleId, request)
        );
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(
            @PathVariable Integer vehicleId) {

        vehicleServiceImpl.deleteVehicle(vehicleId);

        return ResponseEntity.ok("Vehicle deleted successfully.");
    }

}
