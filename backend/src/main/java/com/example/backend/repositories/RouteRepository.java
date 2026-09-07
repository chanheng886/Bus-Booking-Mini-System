package com.example.backend.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByOriginCityAndDestinationCity(String originCity, String destinationCity);    
    boolean existsByOriginCityAndDestinationCity(String originCity, String destinationCity);
}