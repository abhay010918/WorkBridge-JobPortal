package com.WorkBridge.service;

import com.WorkBridge.configuration.JwtUtil;
import com.WorkBridge.dto.AuthRequest;
import com.WorkBridge.dto.AuthResponse;
import com.WorkBridge.dto.RegisterRequest;
import com.WorkBridge.entity.AppUser;
import com.WorkBridge.entity.Role;
import com.WorkBridge.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AppUserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // ✅ Fixed constructor (removed duplicate BCryptPasswordEncoder)
    public AuthService(AppUserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {
        Optional<AppUser> users = userRepository.findByEmail(request.getEmail());
        if (users.isPresent()) {
            throw new RuntimeException("User already exists with this email.");
        }

        AppUser user = new AppUser();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // ✅ Encode password
        user.setRole(Role.USER);

        userRepository.save(user);
        return "User Registered successfully ";
    }

    public String RecruiterRegister(RegisterRequest request) {
        Optional<AppUser> users = userRepository.findByEmail(request.getEmail());
        if (users.isPresent()) {
            throw new RuntimeException("Recruiter already exists with this email.");
        }

        AppUser user = new AppUser();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // ✅ Encode password
        user.setRole(Role.RECRUITER);

        userRepository.save(user);
        return "Recruiter Registered successfully ";
    }


    public AuthResponse login(AuthRequest request) {
        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
