package com.example.backend.mappers;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.backend.dtos.ResponseDTOs.BookingResponseDTO;
import com.example.backend.dtos.ResponseDTOs.BookingSeatResponseDTO;
import com.example.backend.entities.Booking;
import com.example.backend.enums.BookingStatus;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class BookingMapper {
    private final BookingSeatMapper bookingSeatMapper;
    public BookingResponseDTO toResponse(Booking entity){
        if(entity==null){
            return null;
        }
        BookingResponseDTO dto = new BookingResponseDTO();
        List<BookingSeatResponseDTO> seatDtos = entity.getBookingSeat() != null 
            ? entity.getBookingSeat()
                .stream()
                .map(bookingSeatMapper::toResponse)
                .collect(Collectors.toList()) 
            : Collections.emptyList();

        dto.setId(entity.getId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setBookingCode(entity.getBookingCode());
        dto.setTripScheduleid(entity.getSchedule() != null ? entity.getSchedule().getId() : null);
        dto.setOriginCity(entity.getSchedule().getRoute() != null ? entity.getSchedule().getRoute().getOriginCity() : null);
        dto.setDestinationCity(entity.getSchedule().getRoute() != null ? entity.getSchedule().getRoute().getDestinationCity() : null);
        dto.setDepartureTime(entity.getSchedule() != null ? entity.getSchedule().getDepartureTime() : null);
        dto.setArrivalTime(entity.getSchedule() != null ? entity.getSchedule().getArrivalTime() : null);
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setBookingStatus(BookingStatus.valueOf(entity.getBookingStatus().name()));
        dto.setSeats(seatDtos);
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
