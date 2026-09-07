package com.example.backend.services;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.dtos.RequestDTOs.RouteRequestDTO;
import com.example.backend.dtos.ResponseDTOs.RouteResponseDTO;
import com.example.backend.entities.Route;
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

    public RouteResponseDTO getRouteByOriginCityAndDestinationCity(String oridinCity, String destinationCity){
        Route route = routeRepository.findByOriginCityAndDestinationCity(oridinCity, destinationCity)
            .orElseThrow(() -> new RuntimeException("Route from: " + oridinCity + " to " + destinationCity + " Not Found!!"));
        return routeMapper.toResponse(route);
    }  

    //✅ Create Route (This is only allow for admin to use)
    @Transactional 
    public RouteResponseDTO createRoute(RouteRequestDTO dto){
        if(dto.getOriginCity().equalsIgnoreCase(dto.getDestinationCity())){
            throw new IllegalArgumentException("Route can not be the same name! please try again!");
        }
        if(routeRepository.existsByOriginCityAndDestinationCity(dto.getOriginCity(), dto.getDestinationCity())){
            throw new IllegalArgumentException("Route from: " + dto.getOriginCity() + " to " + dto.getDestinationCity() + " is already exists!!");
        }
        Route route = routeMapper.toEntity(dto);
        Route save = routeRepository.save(route);

        return  routeMapper.toResponse(save);
    }

    //✅ Update Route (This functoin only allow for admin to use)
    @Transactional 
    public RouteResponseDTO updateRoute(Long id, RouteRequestDTO dto){
        if(dto.getOriginCity().equalsIgnoreCase(dto.getDestinationCity())){
            throw new IllegalArgumentException("Route can not be the same!! Try again!");
        }
        Route route = routeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Route with id: " + id + " Not found!!"));
        Route update = routeMapper.toUpdate(dto, route);
        Route save = routeRepository.save(update);

        return routeMapper.toResponse(save);
    }

    //✅ Delete Route (This function only allow for admin to use)
    @Transactional 
    public void deleteRoute(Long id){
        Route route = routeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Route with id: " + id + " Not Found!"));
        routeRepository.delete(route);
    }
}