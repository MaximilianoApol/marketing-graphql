package com.example.marketing.controller;

import com.example.marketing.dto.LoginRequestDTO;
import com.example.marketing.dto.LoginResponse;
import com.example.marketing.dto.UserRequestDTO;
import com.example.marketing.dto.UserResponseDTO;
import com.example.marketing.service.AuthService; 
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService; 

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody UserRequestDTO request
    ) {
        // Y LLAMAR A authService.register
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequestDTO request
    ) {
        // Y LLAMAR A authService.login
        return ResponseEntity.ok(authService.login(request));
    }
}