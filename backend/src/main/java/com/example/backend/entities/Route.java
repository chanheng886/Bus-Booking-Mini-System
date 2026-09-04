package com.example.backend.entities;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Table (name = "tb_route", 
        uniqueConstraints = {
            @UniqueConstraint (
                name="uk_route_origin_destination", 
                columnNames = {"origin_city", "destination_city"}
            )
        }
    )
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
public class Route {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    // 📍Leaving Location
    @Column (name = "origin_city", nullable = false)
    private String originCity;

    // 📍Arrival Location
    @Column (name = "destination_city", nullable = false)
    private String destinationCity;
    
    @Column (name = "distance_km", nullable = false)
    private double distanceKm;

    @Column (name = "duration_minutes", nullable = false)
    private LocalTime durationMinutes;
}
