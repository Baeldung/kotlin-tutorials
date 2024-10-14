package com.baeldung.security.jwt.domain

data class AuthenticationRequest(
    val username: String,
    val password: String,
)

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
)

data class RefreshTokenRequest(
    val token: String
)

data class TokenResponse(
    val token: String
)