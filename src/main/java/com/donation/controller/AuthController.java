package com.donation.controller;

import com.donation.dto.AuthRequestDTO;
import com.donation.dto.AuthResponseDTO;
import com.donation.dto.UserDTO;
import com.donation.exception.DuplicateResourceException;
import com.donation.exception.UnauthorizedException;
import com.donation.model.User;
import com.donation.model.UserRole;
import com.donation.repository.UserRepository;
import com.donation.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username is already taken");
        }

        if (userRepository.existsByEmail(request.getUsername())) {
            throw new DuplicateResourceException("Email is already registered");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getUsername())
                .lastName(request.getUsername())
                .role(UserRole.USER)
                .isActive(true)
                .build();

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        UserDTO userDTO = mapToUserDTO(user);

        return ResponseEntity.ok(AuthResponseDTO.builder()
                .accessToken(jwt)
                .tokenType("Bearer")
                .expiresIn(86400000L)
                .user(userDTO)
                .message("Registration successful")
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));

        if (!user.getIsActive()) {
            throw new UnauthorizedException("User account is inactive");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        UserDTO userDTO = mapToUserDTO(user);

        return ResponseEntity.ok(AuthResponseDTO.builder()
                .accessToken(jwt)
                .tokenType("Bearer")
                .expiresIn(86400000L)
                .user(userDTO)
                .message("Login successful")
                .build());
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("User not found"));
        return ResponseEntity.ok(mapToUserDTO(user));
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
