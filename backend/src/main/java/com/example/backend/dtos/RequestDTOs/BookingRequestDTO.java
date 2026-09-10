package com.example.backend.dtos.RequestDTOs;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data 
public class BookingRequestDTO {
    @NotNull (message = "User id is required!")
    private Long userId;
    @NotNull (message = "Trip schedule id is required!")
    private Long tripScheduleId;
    @NotEmpty (message = "You need to select seat at least 1")
    private List<BookingSeatRequestDTO> seats;
}
