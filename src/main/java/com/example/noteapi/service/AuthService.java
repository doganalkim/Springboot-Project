package com.example.noteapi.service;

import com.example.noteapi.dto.RegisterResponse;
import com.example.noteapi.dto.LoginDTO;
import com.example.noteapi.dto.RegisterDTO;
import com.example.noteapi.entity.User;
import com.example.noteapi.repository.UserRepository;
import com.example.noteapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public RegisterResponse register(RegisterDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        return new RegisterResponse(
                user.getUsername(),
                user.getRole());
    }
}
