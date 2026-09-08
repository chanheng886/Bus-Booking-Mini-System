package com.example.backend.dtos.ResponseDTOs;
import java.time.LocalDateTime;
import com.example.backend.enums.BusType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data 
public class TripScheduleResponseDTO {
    private Long id;
    private Long busId;
    private String plateNumber;
    private Integer totalSeats;
    private BusType busType;
    private String originCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double basePrice;
    private String status;
}