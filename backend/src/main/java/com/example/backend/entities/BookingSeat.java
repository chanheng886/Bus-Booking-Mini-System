package com.example.backend.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table (name = "tb_booking_seat")
@NoArgsConstructor 
@AllArgsConstructor 
@Data 
@Entity 
public class BookingSeat {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "trip_seat_id", nullable = false)
    private TripSeat tripSeat;

    @Column (name = "price_at_booking", nullable = false)
    private BigDecimal price;
}
