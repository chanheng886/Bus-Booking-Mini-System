package com.example.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.RequestDTOs.TripSeatRequestDTO;
import com.example.backend.dtos.ResponseDTOs.TripSeatResponseDTO;
import com.example.backend.services.TripSeatService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/v1/trip-seat")
@RequiredArgsConstructor 
@Tag (name = "Trip Seat")
public class TripSeatController {
    private final TripSeatService seatService;

    @GetMapping 
    public List<TripSeatResponseDTO> findAllTripSeat(){
        return seatService.getAllTripSeat();
    }
    
    @GetMapping ("/{id}")
    public TripSeatResponseDTO findTripSeatById(@PathVariable Long id){
        return seatService.getTripSeatById(id);
    }

    @GetMapping ("/schedule/{scheduleId}")
    public List<TripSeatResponseDTO> findAllTripSeatByScheduleId(@PathVariable Long scheduleId){
        return seatService.getTripSeatByScheduleId(scheduleId);
    }


    @PostMapping ("/initial/{scheduleId}")
    public List<TripSeatResponseDTO> createInitialSeatForSchedule(@PathVariable Long scheduleId){
        return seatService.initializeSeatsForSchedule(scheduleId);
    }

    @PostMapping 
    public TripSeatResponseDTO createSeatTrip(@Valid @RequestBody TripSeatRequestDTO dto){
        return seatService.createTripSeat(dto);
    }

    @PutMapping ("/{id}")
    public TripSeatResponseDTO updateSeatTrip(@PathVariable Long id,@Valid @RequestBody TripSeatRequestDTO dto){
        return seatService.updateTripSeat(id, dto);
    }

    @DeleteMapping ("/{id}")
    public void deleteSeatTrip(@PathVariable Long id){
        seatService.deleteTripSeat(id);
    }


}
