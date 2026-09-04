package com.example.backend.mappers;

import org.springframework.stereotype.Component;

import com.example.backend.dtos.RequestDTOs.BusRequestDTO;
import com.example.backend.dtos.ResponseDTOs.BusResponseDTO;
import com.example.backend.entities.Bus;
import com.example.backend.enums.BusType;

@Component 
public class BusMapper {
    public Bus toEntity(BusRequestDTO dto){
        if(dto == null){
            return  null;
        }
        Bus bus = new Bus();
        bus.setPlateNumber(dto.getPlateNumber());
        if(dto.getBusType() != null){
            bus.setBusType(BusType.valueOf(dto.getBusType().trim().toUpperCase()));
        }
        bus.setTotalSeats(dto.getTotalSeat());
        return bus;
    }
    public BusResponseDTO toResponse(Bus bus){
        if(bus == null){
            return  null;
        }
        BusResponseDTO busResponseDTO = new BusResponseDTO();
        busResponseDTO.setId(bus.getId());
        busResponseDTO.setPlateNumber(bus.getPlateNumber());
        busResponseDTO.setBusType(bus.getBusType() != null ? bus.getBusType().name() : null);
        busResponseDTO.setTotalSeats(bus.getTotalSeats());
        return busResponseDTO;
    }

    public Bus updateEntityFromDTO(BusRequestDTO dto, Bus bus){
        if(bus == null || dto == null){
            return null;
        }
        bus.setPlateNumber(dto.getPlateNumber());
        if(dto.getBusType() != null){
            bus.setBusType(BusType.valueOf(dto.getBusType().trim().toUpperCase()));
        }
        bus.setTotalSeats(dto.getTotalSeat());

        return bus;
    }
}
