package com.example.backend.dtos.RequestDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data 
public class BookingSeatRequestDTO {
    @NotNull (message = "Trip seat id is require!!")
    private Long tripSeatId;
}
