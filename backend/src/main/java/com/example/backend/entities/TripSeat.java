package com.example.backend.entities;
import java.time.LocalDateTime;
import com.example.backend.enums.SeatStatusEnum;
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

@Table (name = "tb_trip_seat", uniqueConstraints = {
    @UniqueConstraint (
        name = "uk_trip_schedule_seat", 
        columnNames = {"trip_schedule_id", "seat_number"})
    })
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
@Data 
public class TripSeat {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "trip_schedule_id", nullable = false)
    private TripSchedule schedule;

    private String seatNumber;
    
    @Enumerated (EnumType.STRING)
    @Column (name = "seat_status")
    private SeatStatusEnum seatStatus = SeatStatusEnum.AVAILABLE;

    @Column (name = "hold_expires_at", nullable = false)
    private LocalDateTime expireAt;
}