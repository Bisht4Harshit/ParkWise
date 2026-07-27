package com.implementation.ParkWise.service;

import com.implementation.ParkWise.dto.request.ParkingLotRequest;
import com.implementation.ParkWise.dto.response.ParkingLotResponse;
import com.implementation.ParkWise.dto.response.VehicleResponse;
import com.implementation.ParkWise.entity.ParkingLot;
import com.implementation.ParkWise.entity.ParkingSlot;
import com.implementation.ParkWise.entity.User;
import com.implementation.ParkWise.enums.Role;
import com.implementation.ParkWise.enums.SlotType;
import com.implementation.ParkWise.enums.Status;
import com.implementation.ParkWise.repository.ParkingLotRepository;
import com.implementation.ParkWise.repository.ParkingSlotRepository;
import com.implementation.ParkWise.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ParkingLotServiceImpl implements ParkingLotService{

    private final UserRepository userRepo;
    private final ParkingLotRepository parkingLotRepo;
    private final ParkingSlotRepository parkingSlotRepo;

    public ParkingLotServiceImpl(UserRepository userRepo, ParkingLotRepository parkingLotRepo, ParkingSlotRepository parkingSlotRepo) {
        this.userRepo = userRepo;
        this.parkingLotRepo = parkingLotRepo;
        this.parkingSlotRepo = parkingSlotRepo;
    }

    @Override
    public ParkingLotResponse addParkingLot(ParkingLotRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found"));
        if(user.getRole() != Role.OWNER){
            throw new RuntimeException("Only owners can create parking lots.");
        }

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(request.getName());
        parkingLot.setBlockNo(request.getBlockNo());
        parkingLot.setStreet(request.getStreet());
        parkingLot.setCity(request.getCity());
        parkingLot.setState(request.getState());
        parkingLot.setPincode(request.getPincode());
        parkingLot.setTotalSlots(request.getTotalSlots());
        parkingLot.setOpeningTime(request.getOpeningTime());
        parkingLot.setClosingTime(request.getClosingTime());

        parkingLot.setOwner(user);
//        parkingLotRepo.save(parkingLot);

        ParkingLot savedParkingLot = parkingLotRepo.save(parkingLot);

        List<ParkingSlot> slots = new ArrayList<>();
        for(int i = 1; i <= savedParkingLot.getTotalSlots(); i++) {

            ParkingSlot slot = new ParkingSlot();

            slot.setSlotNo("S" + i);
            slot.setStatus(Status.AVAILABLE);
            slot.setSlotType(SlotType.FOUR_WHEELER);
            slot.setParkingLot(savedParkingLot);
            slots.add(slot);
        }
        parkingSlotRepo.saveAll(slots);

        ParkingLotResponse response = new ParkingLotResponse();

        response.setId(savedParkingLot.getId());
        response.setName(savedParkingLot.getName());
        response.setBlockNo(savedParkingLot.getBlockNo());
        response.setStreet(savedParkingLot.getStreet());
        response.setState(savedParkingLot.getState());
        response.setPincode(savedParkingLot.getPincode());
        response.setTotalSlots(savedParkingLot.getTotalSlots());
        response.setOpeningTime(savedParkingLot.getOpeningTime());
        response.setClosingTime(savedParkingLot.getClosingTime());

     return response;
    }

    @Override
    public List<ParkingLotResponse> getMyParkingLots() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("Owner not found"));
        List<ParkingLot> parkingLots = parkingLotRepo.findByOwner(user);
        List<ParkingLotResponse> response = new ArrayList<>();
        for(ParkingLot parkingLot : parkingLots){
            ParkingLotResponse dto = new ParkingLotResponse();
            dto.setId(parkingLot.getId());
            dto.setName(parkingLot.getName());
            dto.setBlockNo(parkingLot.getBlockNo());
            dto.setStreet(parkingLot.getStreet());
            dto.setCity(parkingLot.getCity());
            dto.setState(parkingLot.getState());
            dto.setPincode(parkingLot.getPincode());
            dto.setTotalSlots(parkingLot.getTotalSlots());
            dto.setOpeningTime(parkingLot.getOpeningTime());
            dto.setClosingTime(parkingLot.getClosingTime());

            response.add(dto);
        }
        return response;
    }

    @Override
    public ParkingLotResponse updateParkingLot(Integer parkingId, ParkingLotRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));
        ParkingLot parkingLot = parkingLotRepo.findById(parkingId)
                .orElseThrow(()->new RuntimeException("Parking lot not found"));
        if(!parkingLot.getOwner().getId().equals(user.getId())){
            throw new RuntimeException("You are not allowed to update this parking lot");
        }

        parkingLot.setName(request.getName());
        parkingLot.setBlockNo(request.getBlockNo());
        parkingLot.setStreet(request.getStreet());
        parkingLot.setCity(request.getCity());
        parkingLot.setState(request.getState());
        parkingLot.setPincode(request.getPincode());
        parkingLot.setTotalSlots(request.getTotalSlots());
        parkingLot.setOpeningTime(request.getOpeningTime());
        parkingLot.setClosingTime(request.getClosingTime());

        ParkingLot updatedParkingLot = parkingLotRepo.save(parkingLot);
        ParkingLotResponse response = new ParkingLotResponse();

        response.setId(updatedParkingLot.getId());
        response.setName(updatedParkingLot.getName());
        response.setBlockNo(updatedParkingLot.getBlockNo());
        response.setStreet(updatedParkingLot.getStreet());
        response.setCity(updatedParkingLot.getCity());
        response.setState(updatedParkingLot.getState());
        response.setPincode(updatedParkingLot.getPincode());
        response.setTotalSlots(updatedParkingLot.getTotalSlots());
        response.setOpeningTime(updatedParkingLot.getOpeningTime());
        response.setClosingTime(updatedParkingLot.getClosingTime());


        return response;
    }

    @Override
    public void deleteParkingLot(Integer parkingLotId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));

        ParkingLot parkingLot = parkingLotRepo.findById(parkingLotId)
                .orElseThrow(()->new RuntimeException("Parking lot not found"));

        if(!parkingLot.getOwner().getId().equals(user.getId())){
            throw new RuntimeException("You are not allowed to delete this parking lot");
        }
        parkingLotRepo.delete(parkingLot);
    }

}
