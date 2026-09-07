package com.example.backend.dtos.RequestDTOs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class RouteRequestDTO {
    @NotBlank (message = "Origin city is required!")
    private String originCity;
    private String destinationCity;
    @NotNull  (message = "Destance Km is required!")
    private double destanceKm;
    @NotNull  (message = "Duration Minutes is required!")
    private Integer durationMinutes;
}
