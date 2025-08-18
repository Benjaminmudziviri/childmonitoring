package com.radical.childmonitoring.security.auth.dto;

import com.radical.childmonitoring.security.user.Role;

public record RegisterRequest(
        String username,
        String email,
        String password,
        Role role,
        String deviceId
) {}
