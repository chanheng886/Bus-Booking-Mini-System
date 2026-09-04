package com.example.backend.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.backend.dtos.ResponseDTOs.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 1. Catch DTO @valid annotation failures (@NotBlank, @Email, @Size)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> fieldsError = new HashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            fieldsError.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponseDTO errorDto = ErrorResponseDTO.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Validation Failed")
            .validationErrors(fieldsError)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    //2. Catch wrong email or password during authenticationamanager 
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentials(BadCredentialsException ex){
        ErrorResponseDTO errorDto  = ErrorResponseDTO.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.UNAUTHORIZED.value())
            .error("Unauthorized")
            .message("Inlvaid email or password")
            .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
    }

    //3. Catches duplicate email checks or custom business rule voilations
    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponseDTO errorDTO = ErrorResponseDTO.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO errorDTO = ErrorResponseDTO.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("An unexpected error occurred. Please try again later.")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}