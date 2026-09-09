package com.example.backend.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entities.TripSeat;

public interface TripSeatRepository extends JpaRepository<TripSeat, Long> {
    List<TripSeat> findByScheduleId(Long id);
}
