// package com.example.backend.services.impl;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;

// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.example.backend.dtos.RequestDTOs.BookingRequestDTO;
// import com.example.backend.dtos.RequestDTOs.BookingSeatRequestDTO;
// import com.example.backend.dtos.ResponseDTOs.BookingResponseDTO;
// import com.example.backend.entities.Booking;
// import com.example.backend.entities.BookingSeat;
// import com.example.backend.entities.TripSchedule;
// import com.example.backend.entities.TripSeat;
// import com.example.backend.entities.User;
// import com.example.backend.enums.BookingStatus;
// import com.example.backend.enums.SeatStatusEnum;
// import com.example.backend.mappers.BookingMapper;
// import com.example.backend.mappers.BookingSeatMapper;
// import com.example.backend.repositories.BookingRepository;
// import com.example.backend.repositories.TripScheduleRepository;
// import com.example.backend.repositories.TripSeatRepository;
// import com.example.backend.repositories.UserRepository;
// import com.example.backend.services.BookingService;

// import lombok.RequiredArgsConstructor;

// @Service 
// @RequiredArgsConstructor 
// public class BookingServiceImpl implements BookingService {
//     private final UserRepository userRepository;
//     private final TripScheduleRepository scheduleRepository;
//     private final BookingMapper bookingMapper;
//     private final BookingSeatMapper bookingSeatMapper;
//     private final TripSeatRepository tripSeatRepository;

//     @Transactional 
//     @Override 
//     public BookingResponseDTO createBooking(BookingRequestDTO dto){
//         User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User with id: " + dto.getUserId() + " Not Found!"));
//         TripSchedule schedule = scheduleRepository.findById(dto.getTripScheduleId()).orElseThrow(() -> new RuntimeException("Schedule id: " + dto.getTripScheduleId() + " Not Found!"));

//         BigDecimal unitPrice = schedule.getBasePrice() != null ? schedule.getBasePrice() : BigDecimal.ZERO;
//         Booking booking = new Booking();

//         booking.setUser(user);
//         booking.setSchedule(schedule);
//         booking.setBookingCode("BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
//         booking.setBookingStatus(BookingStatus.PENDING);
//         booking.setCreatedAt(LocalDateTime.now());
//         BigDecimal totalAmount = BigDecimal.ZERO;
//         List<TripSeat> seats = new ArrayList<>();

//         for(BookingSeatRequestDTO seatReq : dto.getSeats()){
//             TripSeat tripSeat = tripSeatRepository.findById(seatReq.getTripSeatId()).orElseThrow(() -> new RuntimeException("Trip seat id: " + seatReq.getTripSeatId() + " Not Found!"));
//             if(!tripSeat.getSchedule().getId().equals(schedule.getId())){
//                 throw new IllegalArgumentException("seat" + tripSeat.getSeatNumber() + " does not belong to schedule id " + schedule.getId());
//             }
//             if(tripSeat.getSeatStatus() != SeatStatusEnum.AVAILABLE){
//                 throw new IllegalArgumentException("seat" + tripSeat.getSeatNumber() + "is already booked");
//             }
//             tripSeat.setSeatStatus(SeatStatusEnum.UNVAILABLE);
//             seats.add(tripSeat);
//             BookingSeat bookingSeat = new BookingSeat();
//             bookingSeat.setTripSeat(tripSeat);
//         }


//     }
// }
