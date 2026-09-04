package com.example.backend.dtos.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class BusResponseDTO {
    private Long id;
    private String plateNumber;
    private String busType;
    private int totalSeats; 
}
