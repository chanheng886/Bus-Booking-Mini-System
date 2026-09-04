package com.example.backend.services;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.dtos.RequestDTOs.BusRequestDTO;
import com.example.backend.dtos.ResponseDTOs.BusResponseDTO;
import com.example.backend.entities.Bus;
import com.example.backend.mappers.BusMapper;
import com.example.backend.repositories.BusRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional (readOnly = true)
public class BusService {
    private final BusRepository busRepository;
    private final BusMapper busMapper;

    //✅ Get All Bus
    public List<BusResponseDTO> getAllBus(){
        return  busRepository.findAll()
            .stream()
            .map(busMapper::toResponse)
            .collect(Collectors.toList());
    }

    // ✅ Get Bus Plate number
    public BusResponseDTO findByPlateNumber(String plateNumber){
        Bus bus = busRepository.findByPlateNumber(plateNumber)
            .orElseThrow(() -> new RuntimeException("Bus with plate number: " + plateNumber + " Not Found!!"));
        return  busMapper.toResponse(bus);
    }

    //✅ Get Bus By ID (Easy for admin to select bus)
    public  BusResponseDTO getBusById(Long id){
        Bus bus = busRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bus with id: " + id + " Not Found!!"));
        return busMapper.toResponse(bus);
    }

    // ✅ Create Bus
    @Transactional 
    public BusResponseDTO createBus(BusRequestDTO dto){
        if(busRepository.existsByPlateNumber(dto.getPlateNumber())){
            throw new IllegalArgumentException("Bus With Plate number: " + dto.getPlateNumber() + " Is Already Exits");
        }
        Bus bus = busMapper.toEntity(dto);
        Bus save = busRepository.save(bus);
        return busMapper.toResponse(save);
    }

    // ✅ Update Bus
    @Transactional 
    public BusResponseDTO updateBus(BusRequestDTO dto, Long id){
        Bus bus = busRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bus with id: " + id + " Not Found!"));
        if(!bus.getPlateNumber().equalsIgnoreCase(dto.getPlateNumber()) && busRepository.existsByPlateNumber(dto.getPlateNumber())){
            throw new IllegalArgumentException("PlateNumber" + dto.getPlateNumber() + "is already use by the other bus!!");
        }
        Bus update = busMapper.updateEntityFromDTO(dto, bus);
        Bus save = busRepository.save(update);

        return busMapper.toResponse(save);
    }

    @Transactional 
    public void deleteBus(Long id){
        Bus bus = busRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bus with id: " + id + " Not Found!!"));
        busRepository.delete(bus);
    }
}