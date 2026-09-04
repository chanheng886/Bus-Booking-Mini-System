package com.example.backend.mappers;
import org.springframework.stereotype.Component;

import com.example.backend.dtos.RequestDTOs.RegisterRequestDTO;
import com.example.backend.dtos.ResponseDTOs.AuthResponse;
import com.example.backend.entities.User;
import com.example.backend.enums.Role;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthMapper {
    public User toEntity(RegisterRequestDTO dto, String encodePassword){
        User user = User.builder()
            .username(dto.getFullname())
            .email(dto.getEmail())
            .password(encodePassword)
            .role(Role.ROLE_USER)
            .build();
        return user;
    }

    public AuthResponse toResponse(User user, String token){
        AuthResponse authResponse = AuthResponse.builder()
            .token(token)
            .fullName(user.getUsername())
            .email(user.getEmail())
            .build();
        return authResponse;
    }
}
