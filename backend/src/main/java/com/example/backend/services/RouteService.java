package com.example.backend.services;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.backend.dtos.ResponseDTOs.RouteResponseDTO;
import com.example.backend.mappers.RouteMapper;
import com.example.backend.repositories.RouteRepository;
import lombok.RequiredArgsConstructor;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class RouteService {
    private final RouteMapper routeMapper;
    private final RouteRepository routeRepository;

    public List<RouteResponseDTO> getAllRoute(){
        return routeRepository.findAll()
            .stream()
            .map(routeMapper::toResponse)
            .collect(Collectors.toList());
    }
}