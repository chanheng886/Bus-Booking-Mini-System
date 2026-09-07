package com.example.backend.dtos.ResponseDTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class RouteResponseDTO {
    private Long id;
    private String originCity;
    private String destinationCity;
    private double destanceKm;
    private Integer durationMinutes;
}
