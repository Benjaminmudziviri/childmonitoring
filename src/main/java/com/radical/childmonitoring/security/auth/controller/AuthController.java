package com.radical.childmonitoring.security.auth.controller;

import com.radical.childmonitoring.security.auth.dto.AuthenticationRequest;
import com.radical.childmonitoring.security.auth.dto.AuthenticationResponse;
import com.radical.childmonitoring.security.auth.dto.RegisterRequest;
import com.radical.childmonitoring.security.auth.service.AthenticationService;
import com.radical.childmonitoring.security.auth.service.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AthenticationService authenticationService;

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    // Logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok().build();
    }
}
