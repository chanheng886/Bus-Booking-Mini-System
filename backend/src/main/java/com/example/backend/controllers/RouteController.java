package com.example.backend.controllers;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dtos.RequestDTOs.RouteRequestDTO;
import com.example.backend.dtos.ResponseDTOs.RouteResponseDTO;
import com.example.backend.services.RouteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/v1/route")
@RequiredArgsConstructor 
@Tag (name = "Route")
public class RouteController {
    private final RouteService routeService;

    @GetMapping 
    public List<RouteResponseDTO> findAllRoute(){
        return routeService.getAllRoute();
    }

    @GetMapping ("from/{originCity}/to/{destinationCity}")
    public RouteResponseDTO findByOriginCityAndDestinationCity(@PathVariable String originCity, @PathVariable String destinationCity){
        return routeService.getRouteByOriginCityAndDestinationCity(originCity, destinationCity);
    }

    //✅ This API route only for admin to use
    @PostMapping("/create")
    public RouteResponseDTO createRoute(@Valid @RequestBody RouteRequestDTO dto){
        return routeService.createRoute(dto);
    }

    @PutMapping ("/update/{id}")
    public RouteResponseDTO updateRoute(@PathVariable Long id, @RequestBody RouteRequestDTO dto){
        return routeService.updateRoute(id, dto);
    }

    @DeleteMapping ("/{id}")
    public void deleteRoute(@PathVariable Long id){
        routeService.deleteRoute(id);
    }
}
