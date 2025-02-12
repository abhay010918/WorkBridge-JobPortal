package com.WorkBridge.controller;


import com.WorkBridge.dto.AuthRequest;
import com.WorkBridge.dto.AuthResponse;
import com.WorkBridge.dto.RegisterRequest;
import com.WorkBridge.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
            ){
        return ResponseEntity.ok( authService.register(request));
    }

    @PostMapping("/register/recruiter")
    public ResponseEntity<String> RecruiterRegister(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok( authService.RecruiterRegister(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request
            ){
        return ResponseEntity.ok(authService.login(request));
    }
}
