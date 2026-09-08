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
public class TripScheduleRequestDTO {
    @NotNull (message = "Bus Id is required!!")
    private Long busId;
    @NotBlank (message = "Route id is required!")
    private Long routeId;
    @NotBlank (message = "Origin city is required!!")
    private String originCity;
    @NotBlank (message = "Destination city is requried!!")
    private String destinationCity;
    @NotBlank (message = "Departure time is requried!!")
    private LocalDateTime departureTime;
    @NotBlank (message = "Arrival time is requried!!")
    private LocalDateTime arrivalTime;
    @NotNull (message = "Base price is required!!")
    private double basePrice;
    private String status = "SCHEDULED";
}
