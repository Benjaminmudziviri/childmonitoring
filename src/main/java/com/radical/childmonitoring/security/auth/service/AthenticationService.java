package com.radical.childmonitoring.security.auth.service;

import com.radical.childmonitoring.security.auth.dto.AuthenticationRequest;
import com.radical.childmonitoring.security.auth.dto.AuthenticationResponse;
import com.radical.childmonitoring.security.auth.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;


public interface AthenticationService {

    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public AuthenticationResponse register(RegisterRequest request);
    public void logout(HttpServletRequest request);
}
