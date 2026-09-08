package com.example.backend.services.impl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.backend.dtos.RequestDTOs.TripScheduleRequestDTO;
import com.example.backend.dtos.ResponseDTOs.TripScheduleResponseDTO;
import com.example.backend.entities.Bus;
import com.example.backend.entities.Route;
import com.example.backend.entities.TripSchedule;
import com.example.backend.mappers.TripScheduleMapper;
import com.example.backend.repositories.BusRepository;
import com.example.backend.repositories.RouteRepository;
import com.example.backend.repositories.TripScheduleRepository;
import com.example.backend.services.TripScheduleService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TripScheduleServiceImpl implements TripScheduleService {
    private final TripScheduleRepository scheduleRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final TripScheduleMapper scheduleMapper;

    @Override 
    @Transactional (readOnly = true)
    public List<TripScheduleResponseDTO> getAllTripSchedule(){
        return scheduleRepository.findAll().stream().map(scheduleMapper::toResponse).collect(Collectors.toList());
    }

    @Override 
    @Transactional (readOnly = true)
    public List<TripScheduleResponseDTO> getScheduleByOriginCityAndDestinationCity(String originCity, String destinationCity){
        return scheduleRepository.findByRoute_OriginCityIgnoreCaseAndRoute_DestinationCityIgnoreCase(originCity, destinationCity)
            .stream()
            .map(scheduleMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override 
    @Transactional (readOnly = true)
    public TripScheduleResponseDTO getTripScheduleByid(Long id){
        TripSchedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Scheudle with id: " + id + " Not Found!!"));
        return scheduleMapper.toResponse(schedule);
    }

    @Override 
    @Transactional 
    public TripScheduleResponseDTO createTripSchedule(TripScheduleRequestDTO dto){
        if(dto.getArrivalTime().isBefore(dto.getDepartureTime())){
            throw new IllegalArgumentException("Arrivals Time cannot be before departure time");
        }
        boolean isOverlappingTrip = scheduleRepository.existsOverlappingTrip(dto.getBusId(), dto.getDepartureTime(), dto.getArrivalTime());
        if(isOverlappingTrip){
            throw new IllegalArgumentException("The bus is already departured on a trip!");
        }
        Bus bus = busRepository.findById(dto.getBusId())
            .orElseThrow(() -> new RuntimeException("Bus id: " + dto.getBusId()+ " Not Found!"));
        Route route = routeRepository.findById(dto.getRouteId())
            .orElseThrow(() -> new RuntimeException("Route id: " + dto.getRouteId() + " Not Found!!"));
        TripSchedule schedule = scheduleMapper.toEntity(dto);
        schedule.setBus(bus);
        schedule.setRoute(route);
        
        TripSchedule save = scheduleRepository.save(schedule);
        return  scheduleMapper.toResponse(save);
    }

    @Override 
    @Transactional 
    public void cancelTripSchedule(Long id){
        TripSchedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip with id: " + id + " Not Found!"));
        schedule.setStatus("CANCELLED");
        scheduleRepository.save(schedule);
    }
}
