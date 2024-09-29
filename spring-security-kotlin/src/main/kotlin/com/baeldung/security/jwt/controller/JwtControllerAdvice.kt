package com.baeldung.security.jwt.controller

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class JwtControllerAdvice {
    @ExceptionHandler(value = [ExpiredJwtException::class, AuthenticationException::class, SignatureException::class])
    fun handleAuthenticationExceptions(ex: RuntimeException): ResponseEntity<String> {
        return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body("Authentication failed: ${ex.message}")
    }
}