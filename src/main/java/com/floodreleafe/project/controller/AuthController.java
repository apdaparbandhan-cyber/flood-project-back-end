package com.floodreleafe.project.controller;

import com.floodreleafe.project.entity.AdminUser;
import com.floodreleafe.project.repository.AdminUserRepository;
import com.floodreleafe.project.security.JwtTokenProvider;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AdminUser admin = adminUserRepository.findByUsername(request.getUsername()).orElse(null);

        if (admin == null || !passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            return ResponseEntity.status(401).body("Galat Username ya Password!");
        }

        String token = tokenProvider.generateToken(admin.getUsername(), admin.getRole());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "username", admin.getUsername(),
                "role", admin.getRole(),
                "fullName", admin.getFullName()
        ));
    }
}
