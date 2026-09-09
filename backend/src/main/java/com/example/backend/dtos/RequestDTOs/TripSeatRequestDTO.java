package com.example.backend.dtos.RequestDTOs;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data 
public class TripSeatRequestDTO {
    @NotNull (message = "Trip Schedule Id is requried!")
    private Long tripScheduleId;
    @NotBlank (message = "Seat number is required!!")
    private String seatNumber;
    private String seatStatus;
    private LocalDateTime expireAt;
}
