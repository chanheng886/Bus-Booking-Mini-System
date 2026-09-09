package com.example.backend.mappers;
import org.springframework.stereotype.Component;
import com.example.backend.dtos.RequestDTOs.TripSeatRequestDTO;
import com.example.backend.dtos.ResponseDTOs.TripSeatResponseDTO;
import com.example.backend.entities.TripSeat;
import com.example.backend.enums.SeatStatusEnum;

@Component 
public class TripSeatMapper {

    public TripSeat toEntity(TripSeatRequestDTO dto){
        if(dto==null){
            return null;
        }
        TripSeat seat = new TripSeat();
        seat.setSeatNumber(dto.getSeatNumber());
        if(dto.getSeatStatus() != null && !dto.getSeatStatus().isBlank()){
            try{
                seat.setSeatStatus(SeatStatusEnum.valueOf(dto.getSeatStatus().toUpperCase()));
            }catch(IllegalArgumentException e){
                seat.setSeatStatus(SeatStatusEnum.AVAILABLE);
            }
        }else{
            seat.setSeatStatus(SeatStatusEnum.AVAILABLE);
        }
        seat.setExpireAt(dto.getExpireAt());
        return seat;
    }

    public TripSeatResponseDTO toResponse(TripSeat seat){
        if(seat==null){
            return null;
        }
        TripSeatResponseDTO dto = new TripSeatResponseDTO();
        dto.setId(seat.getId());
        if(seat.getSchedule() != null){
            dto.setTripScheduleId(seat.getSchedule().getId());
        }
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setStatus(seat.getSeatStatus() != null ? seat.getSeatStatus().name() : null);
        dto.setExpireAt(seat.getExpireAt());

        return dto;
    }
}
