package com.example.marketing.service;

import com.example.marketing.dto.LoginRequestDTO;
import com.example.marketing.dto.LoginResponse;
import com.example.marketing.dto.UserRequestDTO;
import com.example.marketing.dto.UserResponseDTO;
import com.example.marketing.model.Role;
import com.example.marketing.model.User;
import com.example.marketing.repository.RoleRepository;
import com.example.marketing.repository.UserRepository;
//import com.example.marketing.service.JwtService; // Asegúrate de tener esta clase
import lombok.RequiredArgsConstructor;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    // Esta clase SÍ puede inyectar todo esto
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    //private final PasswordEncoder passwordEncoder;
    //private final JwtService jwtService; // Descomenta esto
    //private final AuthenticationManager authenticationManager; // Descomenta esto

    /**
     * Registra un nuevo usuario (movido desde UserService)
     */
    public UserResponseDTO register(UserRequestDTO request) {

        Role userRole = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con ID: " + request.roleId()));

        var user = User.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(request.password()) // Encripta
                .role(userRole)
                .isActive(true)
                .creationDate(OffsetDateTime.now())
                .build();
        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getUserId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole().getRoleName(),
                savedUser.isActive(),
                savedUser.getCreationDate());
    }

//     public LoginResponse login(LoginRequestDTO request) {

//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         request.email(),
//                         request.password()
//                 )
//         );

//         var userDetails = userRepository.findByEmail(request.email())
//                 .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        
//         var jwtToken = jwtService.generateToken(userDetails);
//         return new LoginResponse(jwtToken);
//     }
}