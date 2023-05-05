package com.check24.services;

import com.check24.model.AuthRequest;
import com.check24.model.AuthResponse;
import com.check24.utils.JwtTokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenGenerator generator;

    public AuthenticationService(AuthenticationManager authenticationManager, UserService userService, JwtTokenGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.generator = generator;
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());

        final String token = this.generator.generateToken(userDetails);

        return new AuthResponse.Builder().setJwtToken(token).build();
    }
}
