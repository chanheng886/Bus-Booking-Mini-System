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
import com.example.backend.dtos.RequestDTOs.BusRequestDTO;
import com.example.backend.dtos.ResponseDTOs.BusResponseDTO;
import com.example.backend.services.BusService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/v1/bus")
@RequiredArgsConstructor 
public class BusController {
    private final BusService busService;

    @GetMapping 
    @Operation (summary = "Get All Buses")
    public List<BusResponseDTO> getAllBuses(){
        return busService.getAllBus();
    }

    @GetMapping ("/{id}")
    @Operation (summary = "Admin can select bus by id")
    public BusResponseDTO getBusById(@PathVariable Long id){
        return  busService.getBusById(id);
    }

    @GetMapping ("/plate/{plate_number}") 
    @Operation (summary = "Find bus by bus's plate number")
    public BusResponseDTO getByPlateNumber(@PathVariable String plate_number){
        return busService.findByPlateNumber(plate_number);
    }

    @PostMapping ("/create/")
    @Operation (summary = "Create Bus")
    public BusResponseDTO createBus(@RequestBody BusRequestDTO dto){
        return busService.createBus(dto);
    }

    @PutMapping ("/update/{id}")
    @Operation (summary = "Update Bus information")
    public BusResponseDTO updateBus(@PathVariable Long id, @RequestBody BusRequestDTO dto){
        return busService.updateBus(dto, id);
    }

    @DeleteMapping ("/delete/{id}")
    @Operation (summary = "Delete bus")
    public void deleteBus(@PathVariable Long id){
        busService.deleteBus(id);
    }
}