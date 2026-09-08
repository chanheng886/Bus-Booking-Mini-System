package com.example.backend.entities;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table (name = "tb_trip_schedule", 
    uniqueConstraints = 
    @UniqueConstraint (
        name = "uk_bus_departure_time", 
        columnNames = {"bus_id", "departure_time"}
    )
)
@NoArgsConstructor 
@AllArgsConstructor 
@Data 
@Entity 
public class TripSchedule {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne (fetch = FetchType.LAZY) 
    @JoinColumn (name = "route_id", nullable = false)
    private Route route;

    @Column (name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column (name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column (name = "base_price", nullable = false)
    private double basePrice;

    private String status;
}