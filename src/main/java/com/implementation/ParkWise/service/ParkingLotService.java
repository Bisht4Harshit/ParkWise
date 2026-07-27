package com.implementation.ParkWise.service;

import com.implementation.ParkWise.dto.request.ParkingLotRequest;
import com.implementation.ParkWise.dto.response.ParkingLotResponse;

import java.util.List;

public interface ParkingLotService {
    public ParkingLotResponse addParkingLot(ParkingLotRequest request);

    public List<ParkingLotResponse> getMyParkingLots();

    public ParkingLotResponse updateParkingLot(Integer parkingId, ParkingLotRequest request);

    public void deleteParkingLot(Integer parkingLotId);

}
