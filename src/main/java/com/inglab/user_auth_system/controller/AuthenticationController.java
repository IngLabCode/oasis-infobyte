package com.inglab.user_auth_system.controller;

import com.inglab.user_auth_system.dto.AuthenticationRequest;
import com.inglab.user_auth_system.dto.AuthenticationResponse;
import com.inglab.user_auth_system.dto.RegisterRequest;
import com.inglab.user_auth_system.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user in the system and returns an authentication token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid input data."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @Operation(
            summary = "Authenticate an existing user",
            description = "Validates user credentials and returns a JWT token for authentication."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful."),
            @ApiResponse(responseCode = "401", description = "Invalid credentials."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @Operation(
            summary = "Refresh authentication token",
            description = "Generates a new JWT token using the provided refresh token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully."),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
