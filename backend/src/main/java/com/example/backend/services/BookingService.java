package com.example.backend.services;

import java.util.List;

import com.example.backend.dtos.RequestDTOs.BookingRequestDTO;
import com.example.backend.dtos.ResponseDTOs.BookingResponseDTO;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO dto);
    BookingResponseDTO getBookingById(Long id);
    BookingResponseDTO getBookingByCode(String code);
    List<BookingResponseDTO> getBookingByUserId(Long id);
    List<BookingResponseDTO> getAllBooking();
    BookingResponseDTO confirmBooking();
    BookingResponseDTO cancelBooking();
}
