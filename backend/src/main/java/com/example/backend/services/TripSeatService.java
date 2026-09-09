package com.example.backend.services;
import java.util.List;
import com.example.backend.dtos.RequestDTOs.TripSeatRequestDTO;
import com.example.backend.dtos.ResponseDTOs.TripSeatResponseDTO;

public interface TripSeatService {
    List<TripSeatResponseDTO> getAllTripSeat();
    List<TripSeatResponseDTO> getTripSeatByScheduleId(Long id);
    List<TripSeatResponseDTO> initializeSeatsForSchedule(Long tripScheduleId);
    TripSeatResponseDTO getTripSeatById(Long id);
    TripSeatResponseDTO createTripSeat(TripSeatRequestDTO dto);
    TripSeatResponseDTO updateTripSeat(Long id, TripSeatRequestDTO dto);
    void deleteTripSeat(Long id);
}
