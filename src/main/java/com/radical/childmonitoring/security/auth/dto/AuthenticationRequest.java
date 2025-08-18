package com.radical.childmonitoring.security.auth.dto;

public record AuthenticationRequest(
        String usernameOrEmail,
        String password,
        String deviceId
) {}

