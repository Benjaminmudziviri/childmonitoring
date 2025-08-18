package com.radical.childmonitoring.security.auth.dto;

import com.radical.childmonitoring.security.user.Role;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken,
        Role role
) {}

