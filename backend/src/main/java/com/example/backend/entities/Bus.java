package com.example.backend.entities;

import com.example.backend.enums.BusType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_buses")
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
@Entity 
@Data 
public class Bus {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate_number", nullable = false, unique = true)
    private String plateNumber;

    @Enumerated (EnumType.STRING)
    @Column (name = "bus_type", nullable = false)
    private BusType busType;
   
    @Column (name = "total_seat", nullable = false)
    private int totalSeats;
}