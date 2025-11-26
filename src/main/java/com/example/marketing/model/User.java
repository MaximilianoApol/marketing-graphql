package com.example.marketing.model; // O donde pongas tus modelos

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User /*implements UserDetails*/ { // <-- ¡IMPORTANTE!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String password; // El campo se llama 'password' para que Spring lo entienda

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "creation_date", nullable = false)
    private OffsetDateTime creationDate;

    // --- Relación: Muchos Usuarios tienen Un Rol ---
    @ManyToOne(fetch = FetchType.EAGER) // EAGER es útil para cargar el rol con el usuario
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // --- MÉTODOS REQUERIDOS POR UserDetails ---

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     // Aquí le decimos a Spring Security qué rol tiene el usuario
    //     return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    // }

    // @Override
    // public String getPassword() {
    //     return password; // Devuelve el hash de la contraseña
    // }

    // @Override
    // public String getUsername() {
    //     return email; // Usamos el email como "username" para el login
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    //     return true; // Asumimos que las cuentas no expiran
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return true; // Asumimos que no se bloquean
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return true; // Asumimos que las credenciales no expiran
    // }

    // @Override
    // public boolean isEnabled() {
    //     return isActive; // Usamos el campo 'is_active' de nuestra BD
    // }
}