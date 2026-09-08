package com.example.backend.services;
import java.util.List;

import com.example.backend.dtos.RequestDTOs.TripScheduleRequestDTO;
import com.example.backend.dtos.ResponseDTOs.TripScheduleResponseDTO;
 
public interface TripScheduleService {
    List<TripScheduleResponseDTO> getAllTripSchedule();
    List<TripScheduleResponseDTO> getScheduleByOriginCityAndDestinationCity(String originCity, String destinationCity);
    TripScheduleResponseDTO getTripScheduleByid(Long id);
    TripScheduleResponseDTO createTripSchedule(TripScheduleRequestDTO dto);
    void cancelTripSchedule(Long id);
}