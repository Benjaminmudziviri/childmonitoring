package com.radical.childmonitoring.security.auth.service;


import com.radical.childmonitoring.security.auth.dto.AuthenticationRequest;
import com.radical.childmonitoring.security.auth.dto.AuthenticationResponse;
import com.radical.childmonitoring.security.auth.dto.RegisterRequest;
import com.radical.childmonitoring.security.config.JwtService;
import com.radical.childmonitoring.security.token.Token;
import com.radical.childmonitoring.security.token.TokenRepository;
import com.radical.childmonitoring.security.user.Role;
import com.radical.childmonitoring.security.user.User;
import com.radical.childmonitoring.security.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AthenticationService{

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Step 1: Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.usernameOrEmail(),
                        request.password()
                )
        );

        // Step 2: Load user from DB
        User user = userRepository.findByUsernameOrEmail(
                        request.usernameOrEmail(),
                        request.usernameOrEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 3: Enforce one-device-per-account (optional)
        revokeAllTokensForUser(user);

        // Step 4: Save current device ID
        user.setDeviceId(request.deviceId());
        userRepository.save(user);

        // Step 5: Generate tokens
        // Step 5: Generate tokens
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user, request.deviceId());


        // Step 6: Save refresh token
        saveUserToken(user, refreshToken, request.deviceId());

        Role role = user.getRole();
        // Step 7: Return tokens to frontend/app
        return new AuthenticationResponse(accessToken, refreshToken,role);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        // Step 1: Check if user already exists
        if (userRepository.findByUsernameOrEmail(request.username(), request.email()).isPresent()) {
            throw new RuntimeException("User already exists with that username or email.");
        }

        // Step 2: Create user entity
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password())); // encode password
        user.setRole(request.role());
        user.setDeviceId(request.deviceId());

        // Step 3: Save user
        user = userRepository.save(user);

        // Step 4: Generate tokens
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user, request.deviceId());

        // Step 5: Save refresh token
        saveUserToken(user, refreshToken, request.deviceId());

        Role role = user.getRole();
        // Step 6: Return tokens
        return new AuthenticationResponse(accessToken, refreshToken,role);
    }

    public void logout(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        final String token;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        token = authHeader.substring(7); // Remove "Bearer "

        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        if (storedToken != null) {
            storedToken.setRevoked(true);
            storedToken.setExpired(true);
            tokenRepository.save(storedToken);
        }

        // Optional: also unlink deviceId
        User user = storedToken.getUser();
        user.setDeviceId(null);
        userRepository.save(user);
    }


    private void saveUserToken(User user, String refreshToken, String deviceId) {
        Token token = Token.builder()
                .user(user)
                .token(refreshToken)
                .deviceId(deviceId)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllTokensForUser(User user) {
        List<Token> validTokens = tokenRepository.findAllByUser(user);
        if (validTokens.isEmpty()) return;

        for (Token t : validTokens) {
            t.setExpired(true);
            t.setRevoked(true);
        }
        tokenRepository.saveAll(validTokens);
    }

}

