package com.example.backend.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entities.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByPlateNumber(String plateNumber);    
    Boolean existsByPlateNumber(String plateNumber);
}
