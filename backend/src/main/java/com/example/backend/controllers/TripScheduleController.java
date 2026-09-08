package com.example.backend.controllers;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dtos.RequestDTOs.TripScheduleRequestDTO;
import com.example.backend.dtos.ResponseDTOs.TripScheduleResponseDTO;
import com.example.backend.services.TripScheduleService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/v1/trip-schedule")
@Tag (name = "Trip Schedule")
public class TripScheduleController {
    private final TripScheduleService scheduleService;

    @GetMapping 
    public List<TripScheduleResponseDTO> findAllTripSchedule(){
        return scheduleService.getAllTripSchedule();
    }

    @GetMapping ("/route/{originCity}/and{destinationCity}")
    public List<TripScheduleResponseDTO> findAllTripScheduleByOriginCityAndDestinationCity(@PathVariable String originCity, @PathVariable String destinationCity){
        return scheduleService.getScheduleByOriginCityAndDestinationCity(originCity, destinationCity);
    }

    @GetMapping ("/{id}")
    public TripScheduleResponseDTO findTripById(@PathVariable Long id){
        return scheduleService.getTripScheduleByid(id);
    }

    @PostMapping ("/create")
    public TripScheduleResponseDTO createTripSchedule(@Valid @RequestBody TripScheduleRequestDTO dto){
        return scheduleService.createTripSchedule(dto);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> cancelSchedule(@PathVariable Long id){
        scheduleService.cancelTripSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
