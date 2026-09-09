package com.example.backend.services.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.backend.dtos.RequestDTOs.TripSeatRequestDTO;
import com.example.backend.dtos.ResponseDTOs.TripSeatResponseDTO;
import com.example.backend.entities.TripSchedule;
import com.example.backend.entities.TripSeat;
import com.example.backend.enums.SeatStatusEnum;
import com.example.backend.mappers.TripSeatMapper;
import com.example.backend.repositories.TripScheduleRepository;
import com.example.backend.repositories.TripSeatRepository;
import com.example.backend.services.TripSeatService;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TripSeatServiceImpl implements TripSeatService  {
    private final TripSeatMapper seatMapper;
    private final TripSeatRepository seatRepository;
    private final TripScheduleRepository scheduleRepository;

    @Transactional 
    @Override 
    public List<TripSeatResponseDTO> getAllTripSeat(){
        return seatRepository.findAll()
            .stream()
            .map(seatMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional 
    @Override 
    public List<TripSeatResponseDTO> getTripSeatByScheduleId(Long id){
        if(!scheduleRepository.existsById(id)){
            throw new IllegalArgumentException("Schedule with id: " + id + " Not Found!");
        }
        return seatRepository.findByScheduleId(id).stream().map(seatMapper::toResponse).collect(Collectors.toList());
    }

    @Transactional 
    @Override 
    public TripSeatResponseDTO getTripSeatById(Long id){
        TripSeat seat = seatRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Seat with id: " + id + " Not Found!!"));
        return  seatMapper.toResponse(seat);
    }

    @Transactional 
    @Override 
    public List<TripSeatResponseDTO> initializeSeatsForSchedule(Long tripScheduleId){
        TripSchedule schedule = scheduleRepository.findById(tripScheduleId).orElseThrow(() -> new RuntimeException("Schedule id: " + tripScheduleId + " Not Found!!"));
        int totalSeat = schedule.getBus().getTotalSeats();
        List<TripSeat> seats = new ArrayList<>();
        for(int i = 1; i <= totalSeat ; i++){
            TripSeat seat = new TripSeat();
            seat.setSeatNumber(String.format("Seat %02d", i));
            seat.setSeatStatus(SeatStatusEnum.AVAILABLE);
            seat.setSchedule(schedule);
            seat.setExpireAt(null);
            seats.add(seat);
        }

        List<TripSeat> saveSeat = seatRepository.saveAll(seats);
        return saveSeat
            .stream()
            .map(seatMapper::toResponse)
            .collect(Collectors.toList());

    }

    @Transactional 
    @Override 
    public TripSeatResponseDTO createTripSeat(TripSeatRequestDTO dto){
        TripSchedule schedule = scheduleRepository.findById(dto.getTripScheduleId())
            .orElseThrow(() -> new RuntimeException("Trip Schedule id: " + dto.getTripScheduleId() + " Not Found!!"));
        TripSeat seat = seatMapper.toEntity(dto);
        seat.setSchedule(schedule);
        TripSeat save = seatRepository.save(seat);

        return seatMapper.toResponse(save);
    }

    @Transactional 
    @Override 
    public TripSeatResponseDTO updateTripSeat(Long id, TripSeatRequestDTO dto){
        TripSeat seat = seatRepository.findById(id).orElseThrow(() -> new RuntimeException("Seat with id: " + id + " Not Found!!"));
        seat.setSeatNumber(dto.getSeatNumber());
        if(dto.getSeatStatus() != null && !dto.getSeatStatus().isBlank()){
            seat.setSeatStatus(SeatStatusEnum.valueOf(dto.getSeatStatus().toUpperCase()));
        }
        if(dto.getExpireAt() != null){
            seat.setExpireAt(dto.getExpireAt());
        }
        TripSeat update = seatRepository.save(seat);
        return seatMapper.toResponse(update);
    }

    @Transactional 
    @Override 
    public void deleteTripSeat(Long id){
        TripSeat seat = seatRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Seat with id: " + id + " Not Found!"));
        seatRepository.delete(seat);
    }
}
