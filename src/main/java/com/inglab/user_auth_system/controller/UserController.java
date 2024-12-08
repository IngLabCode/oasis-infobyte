package com.inglab.user_auth_system.controller;

import com.inglab.user_auth_system.dto.ChangePasswordRequest;
import com.inglab.user_auth_system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @Operation(
            summary = "Change user password",
            description = "Allows a user to change their password by providing the old and new passwords."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password successfully updated."),
            @ApiResponse(responseCode = "400", description = "Invalid input data or password mismatch."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access. The user must be authenticated."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody @Parameter(description = "Request body containing old and new password.") ChangePasswordRequest request,
            @Parameter(description = "Authenticated user making the password change request.") Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
