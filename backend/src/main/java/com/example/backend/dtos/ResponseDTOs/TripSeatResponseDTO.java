package com.example.backend.dtos.ResponseDTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data 
public class TripSeatResponseDTO {
    private Long id;
    private Long tripScheduleId;
    private String seatNumber;
    private String status;
    private LocalDateTime expireAt;
}
