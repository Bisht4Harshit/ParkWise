package com.implementation.ParkWise.dto.response;

import com.implementation.ParkWise.enums.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class BookingResponse {

    private Integer id;
    private String bookingNo;
    private String slotNo;
    private String vehicleRegistrationNo;
    private BookingStatus status;
    private BigDecimal amount;
    private String parkingLotName;
    private LocalDateTime bookingStartTime;
    private LocalDateTime bookingEndTime;

}
