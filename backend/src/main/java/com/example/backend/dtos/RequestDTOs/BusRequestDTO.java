package com.example.backend.dtos.RequestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class BusRequestDTO {
    @NotBlank (message = "Plate Number is required")
    @Size (max = 12, min = 6, message = "Plate number should be 6-12")
    private String plateNumber;

    @NotBlank (message = "Bus Type is requried")
    private String busType;

    @Size (min = 1, message = "Seats cannot be 0")
    @NotNull (message = "Seats is required!")
    private int totalSeat; 
}
