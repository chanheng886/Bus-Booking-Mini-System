package com.example.backend.repositories;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend.entities.TripSchedule;

public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {
    List<TripSchedule> findByRoute_OriginCityIgnoreCaseAndRoute_DestinationCityIgnoreCase(String originCity, String destinationCity);

    @Query (""" 
        SELECT COUNT(ts) > 0 FROM TripSchedule ts
        WHERE ts.bus.id = :busId
        AND ts.status != 'CANCELLED'
        AND (:newDepartureTime < ts.arrivalTime AND :newArrivalTime > ts.departureTime)
    """)
    boolean existsOverlappingTrip(
        @Param ("busId") Long busId,
        @Param ("newDepartureTime") LocalDateTime newDepartureTime,
        @Param ("newArrivalTime") LocalDateTime newArrivalTime
    );
}