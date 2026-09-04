package com.example.backend.mappers;
import org.springframework.stereotype.Component;
import com.example.backend.dtos.RequestDTOs.RouteRequestDTO;
import com.example.backend.dtos.ResponseDTOs.RouteResponseDTO;
import com.example.backend.entities.Route;

@Component 
public class RouteMapper {
    public Route toEntity(RouteRequestDTO dto){
        if(dto==null){
            return  null;
        }
        Route route = new Route();
        route.setOriginCity(dto.getOriginCity());
        route.setDestinationCity(dto.getDestinationCity());
        route.setDistanceKm(dto.getDestanceKm());
        return route;
    }
    public RouteResponseDTO toResponse(Route route){
        if(route == null){
            return  null;
        }
        RouteResponseDTO dto = new RouteResponseDTO();
        dto.setId(route.getId());
        dto.setOriginCity(route.getOriginCity());
        dto.setDestinationCity(route.getDestinationCity());
        dto.setDurationMinutes(route.getDurationMinutes());
        return dto;
    }
    public Route toUpdate(RouteRequestDTO dto, Route route){
        if(dto == null || route == null){
            return  null;
        }
        route.setOriginCity(dto.getOriginCity());
        route.setDestinationCity(dto.getDestinationCity());
        route.setDurationMinutes(dto.getDurationMinutes());
        return  route;
    }
}