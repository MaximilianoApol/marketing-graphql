package com.example.marketing.service;

import com.example.marketing.dto.*;
import com.example.marketing.model.Role;
import com.example.marketing.model.User;
import com.example.marketing.repository.RoleRepository;
import com.example.marketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager; // Descomentar imports
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;        // Descomentar
    private final JwtService jwtService;                  // Descomentar
    private final AuthenticationManager authenticationManager; // Descomentar

    public UserResponseDTO register(UserRequestDTO request) {
        @SuppressWarnings("null")
        Role userRole = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado"));

        var user = User.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password())) // <--- IMPORTANTE: ENCRIPTAR
                .role(userRole)
                .isActive(true)
                .creationDate(OffsetDateTime.now())
                .build();
        @SuppressWarnings("null")
        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getUserId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole().getRoleName(),
                savedUser.isActive(),
                savedUser.getCreationDate());
    }

    public LoginResponse login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken);
    }
}