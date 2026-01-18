package com.example.noteapi.controller;

import com.example.noteapi.dto.LoginDTO;
import com.example.noteapi.dto.LoginResponse;
import lombok.extern.java.Log;
import com.example.noteapi.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.noteapi.dto.RegisterResponse;
import com.example.noteapi.dto.ErrorResponse;
import com.example.noteapi.dto.RegisterDTO;
import com.example.noteapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterDTO request) {
        try {
            User user = authService.register(request);
            return ResponseEntity.ok(
                    new RegisterResponse(
                            user.getUsername(),
                            user.getRole())
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO request) {
        try {
            String token = authService.login(request);
            return ResponseEntity.ok(
                    new LoginResponse(token)
            );
        } catch (BadCredentialsException e) {
            ErrorResponse error = new ErrorResponse(
                    "Invalid username or password",
                    HttpStatus.UNAUTHORIZED.value()
            );
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error);
        } catch (Exception e) {
            // ADD THIS LINE TO SEE THE ACTUAL ERROR
            e.printStackTrace(); // or use logger.error("Login failed", e);
            ErrorResponse error = new ErrorResponse(
                    "Login failed",
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(error);
        }
    }
}
