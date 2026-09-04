package com.example.backend.services;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.backend.dtos.RequestDTOs.AuthRequestDTO;
import com.example.backend.dtos.RequestDTOs.RegisterRequestDTO;
import com.example.backend.dtos.ResponseDTOs.AuthResponse;
import com.example.backend.entities.User;
import com.example.backend.mappers.AuthMapper;
import com.example.backend.repositories.UserRepository;
import com.example.backend.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final AuthMapper authMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    // ✅✅ Register
    public AuthResponse register(RegisterRequestDTO dto){
        if(repository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Email is already registered!");
        }
        String encodePassword = encoder.encode(dto.getPassword());
        User user = authMapper.toEntity(dto, encodePassword);
        User save = repository.save(user);
        String token = jwtService.generateToken(save);

        return authMapper.toResponse(user, token);
    }

    // ✅✅ Login
    public AuthResponse login(AuthRequestDTO dto){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        User user = repository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Email not found!"));
        String token = jwtService.generateToken(user);
        return authMapper.toResponse(user, token);
    }   
}
