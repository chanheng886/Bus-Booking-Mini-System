package com.example.backend.mappers;

import org.springframework.stereotype.Component;

import com.example.backend.dtos.RequestDTOs.TripScheduleRequestDTO;
import com.example.backend.dtos.ResponseDTOs.TripScheduleResponseDTO;
import com.example.backend.entities.TripSchedule;

@Component 
public class TripScheduleMapper {
    public TripSchedule toEntity(TripScheduleRequestDTO dto){
        if(dto == null){
            return null;
        }
        TripSchedule schedule = new TripSchedule();
        schedule.setDepartureTime(dto.getDepartureTime());
        schedule.setBasePrice(dto.getBasePrice());
        schedule.setStatus(dto.getStatus());
        return schedule;
    }

    public TripSchedule toUpdate(TripScheduleRequestDTO dto, TripSchedule schedule){
        if(dto == null || schedule==null){
            return schedule;
        }
        schedule.setDepartureTime(dto.getDepartureTime());
        schedule.setBasePrice(dto.getBasePrice());
        schedule.setStatus(dto.getStatus());
        return schedule;
    }

    public TripScheduleResponseDTO toResponse(TripSchedule tripSchedule){
        if(tripSchedule == null){
            return null;
        }
        TripScheduleResponseDTO dto = new TripScheduleResponseDTO();
        dto.setId(tripSchedule.getId());
        if(tripSchedule.getBus() != null){
            dto.setBusId(tripSchedule.getBus().getId());
            dto.setPlateNumber(tripSchedule.getBus().getPlateNumber());
            dto.setTotalSeats(tripSchedule.getBus().getTotalSeats());
            dto.setBusType(tripSchedule.getBus().getBusType());
        }   
        if(tripSchedule.getRoute() != null){
            dto.setOriginCity(tripSchedule.getRoute().getOriginCity());
            dto.setDestinationCity(tripSchedule.getRoute().getDestinationCity());
        }

        dto.setDepartureTime(tripSchedule.getDepartureTime());
        dto.setArrivalTime(tripSchedule.getArrivalTime()); 
        dto.setBasePrice(tripSchedule.getBasePrice());
        dto.setStatus(tripSchedule.getStatus());

        return dto;
    }
}
