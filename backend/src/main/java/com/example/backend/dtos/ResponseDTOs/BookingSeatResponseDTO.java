package com.example.backend.dtos.ResponseDTOs;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data 
public class BookingSeatResponseDTO {
    private Long id;
    private Long tripSeatId;
    private String seatNumber;
    private BigDecimal priceAtBooking;
}
