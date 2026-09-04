package com.example.backend.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.dtos.RequestDTOs.AuthRequestDTO;
import com.example.backend.dtos.RequestDTOs.RegisterRequestDTO;
import com.example.backend.dtos.ResponseDTOs.AuthResponse;
import com.example.backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PutMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(dto));
    }

    @PutMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequestDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}
