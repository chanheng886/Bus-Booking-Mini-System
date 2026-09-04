package com.example.backend.dtos.RequestDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "Full name is required!")
    private String fullname;

    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid email, please try again!")
    private String email;

    @Size(min = 6,max = 15, message = "Password must be 6 - 15")
    private String password;
}
