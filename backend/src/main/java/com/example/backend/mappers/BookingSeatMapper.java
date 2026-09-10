package com.example.backend.mappers;

import org.springframework.stereotype.Component;

import com.example.backend.dtos.ResponseDTOs.BookingSeatResponseDTO;
import com.example.backend.entities.BookingSeat;

@Component 
public class BookingSeatMapper {
    public BookingSeatResponseDTO toResponse(BookingSeat entity){
        BookingSeatResponseDTO dto = new BookingSeatResponseDTO();
        
        dto.setId(entity.getId());
        dto.setTripSeatId(entity.getTripSeat() != null ? entity.getTripSeat().getId() : null);
        dto.setSeatNumber(entity.getTripSeat() != null ? entity.getTripSeat().getSeatNumber() : null);
        dto.setPriceAtBooking(entity.getPrice());
        return dto;
    }
}
