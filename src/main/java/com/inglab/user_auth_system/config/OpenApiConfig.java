package com.inglab.user_auth_system.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT (JSON Web Token) authentication is used to secure API endpoints by ensuring that only authorized users can access protected resources. Once a user successfully logs in with their credentials, they are issued a JWT that contains encrypted user data, including user identity, roles, and other claims. This token must be included in the `Authorization` header of all subsequent API requests to authenticate the user. The token is prefixed with the word 'Bearer' followed by the JWT itself, formatted as: `Authorization: Bearer {token}`.\n\n" +
                "The JWT is a compact and self-contained token that is signed using a secret key or public/private key pairs. The server uses this signature to verify the integrity of the token and ensure it has not been tampered with. The token also includes an expiration time (`exp` claim), ensuring that tokens are only valid for a limited period. Upon expiration, the token is no longer valid, and the user must authenticate again to receive a new token.\n\n" +
                "When a request is made with an invalid, expired, or missing token, the server responds with an HTTP `401 Unauthorized` status, signaling that the user must authenticate before accessing the resource. This type of authentication is stateless, meaning the server does not store any session data. All necessary information is embedded within the JWT itself, which is decoded and verified during each request.\n\n" +
                "For enhanced security, always use HTTPS to transmit JWT tokens. It is crucial to store JWTs securely on the client side (e.g., in HTTP-only cookies or secure storage) to prevent unauthorized access, particularly in the case of XSS (Cross-Site Scripting) attacks. If a user logs out or a token is compromised, it can be invalidated, and a new token can be issued if needed.",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("User Authentication API")
                .version("1.0")
                .description("This API provides secure user authentication and management services. It allows users to register, log in, and manage their account settings, including password updates and account recovery. The API is designed to handle user registration with email validation, secure login using JWT tokens, and token-based authentication for accessing protected resources.\n\n" +
                        "Key features of the API include:\n" +
                        "- **User Registration**: Allows new users to sign up by providing a username, email, and password. An email verification process is included to ensure the validity of the provided email address.\n" +
                        "- **User Login**: Users can authenticate by submitting their username and password. Upon successful authentication, a JWT token is issued, which is used for subsequent requests to authenticate and authorize users.\n" +
                        "- **Password Management**: Users can securely reset or update their passwords through a secure process. This includes sending a password reset link to the registered email address.\n" +
                        "- **JWT Authentication**: The API uses JSON Web Tokens (JWT) to authenticate users for protected routes. The token is passed as part of the `Authorization` header in requests, ensuring that only authorized users can access specific endpoints.\n" +
                        "- **Account Recovery**: A mechanism to help users recover their accounts if they forget their password or experience login issues.\n\n" +
                        "This API is built with security in mind, ensuring user credentials and sensitive information are encrypted and securely transmitted. It leverages modern authentication standards to offer a scalable, stateless, and secure system for managing user access to applications.")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("inglab")
                        .email("eliyevinqlab.02@gmail.com"));

        return new OpenAPI().info(info);
    }
}
