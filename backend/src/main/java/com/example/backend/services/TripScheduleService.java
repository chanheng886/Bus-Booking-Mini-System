package com.example.backend.services;

import org.springframework.stereotype.Service;

import com.example.backend.mappers.TripScheduleMapper;
import com.example.backend.repositories.TripScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TripScheduleService {
    private final TripScheduleMapper mapper;
    private final TripScheduleRepository repository;
    
}
