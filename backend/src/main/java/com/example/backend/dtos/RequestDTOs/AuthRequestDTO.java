package com.example.backend.dtos.RequestDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {
    @NotNull(message = "Email is requried!!")
    @Email(message = "Invalid Email, please try again")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(min = 6, max = 15, message = "Password must be start from 6-15")
    private String password;
}
