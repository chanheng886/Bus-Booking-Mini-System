package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Booking;

/**
 * BookingRepository
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    
}