package com.example.backend.dtos.ResponseDTOs;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.example.backend.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 
@Data 
public class BookingResponseDTO {
    private Long id;
    private Long userId;
    private String bookingCode;

    private Long tripScheduleid;
    private String originCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    
    private BigDecimal totalAmount;
    private BookingStatus bookingStatus;
    private LocalDateTime createdAt;
    List<BookingSeatResponseDTO> seats;

}
