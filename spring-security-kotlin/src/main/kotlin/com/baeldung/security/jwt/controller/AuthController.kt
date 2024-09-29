package com.baeldung.security.jwt.controller

import com.baeldung.security.jwt.domain.AuthenticationRequest
import com.baeldung.security.jwt.domain.AuthenticationResponse
import com.baeldung.security.jwt.domain.RefreshTokenRequest
import com.baeldung.security.jwt.domain.TokenResponse
import com.baeldung.security.jwt.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping
    fun authenticate(
        @RequestBody authRequest: AuthenticationRequest
    ): AuthenticationResponse =
        authenticationService.authentication(authRequest)

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest
    ): TokenResponse = TokenResponse(token = authenticationService.refreshAccessToken(request.token))
}