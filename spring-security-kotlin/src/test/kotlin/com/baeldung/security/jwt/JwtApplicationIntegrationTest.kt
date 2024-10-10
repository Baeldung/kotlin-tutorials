package com.baeldung.security.jwt

import com.baeldung.security.jwt.domain.AuthenticationRequest
import com.baeldung.security.jwt.domain.AuthenticationResponse
import com.baeldung.security.jwt.domain.RefreshTokenRequest
import com.baeldung.security.jwt.domain.TokenResponse
import com.baeldung.security.jwt.service.TokenService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import org.junit.jupiter.api.Test
import org.mockito.Mockito.reset
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [JwtApplication::class])
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class JwtApplicationIntegrationTest {
    @Value("\${jwt.expiredToken}")
    private lateinit var expiredToken: String

    @Autowired
    private lateinit var mockMvc: MockMvc

    @SpyBean
    private lateinit var tokenService: TokenService

    @SpyBean
    private lateinit var userDetailsService: UserDetailsService

    @Test
    fun `access secured endpoint with new token from the refresh token after token expiration`() {
        val authRequest = AuthenticationRequest("email-1@gmail.com", "pass1")
        var jsonRequest = jacksonObjectMapper().writeValueAsString(authRequest)

        var response = mockMvc.perform(
            post("/api/auth")
              .contentType(MediaType.APPLICATION_JSON)
              .content(jsonRequest)
        )
          .andExpect(status().isOk)
          .andExpect(jsonPath("$.accessToken").isNotEmpty)
          .andExpect(jsonPath("$.refreshToken").isNotEmpty).andReturn().response.contentAsString

        val authResponse = jacksonObjectMapper().readValue(response, AuthenticationResponse::class.java)

        // access secured endpoint
        mockMvc.perform(
            get("/api/hello")
              .header("Authorization", "Bearer ${authResponse.accessToken}")
        )
          .andExpect(status().isOk)
          .andExpect(content().string("Hello, Authorized User!"))

        // simulate access token expiration
        `when`(tokenService.extractUsername(authResponse.accessToken))
          .thenThrow(ExpiredJwtException::class.java)

        mockMvc.perform(
            get("/api/hello")
              .header("Authorization", "Bearer ${authResponse.accessToken}")
        )
          .andExpect(status().isForbidden)

        // create a new access token from the refresh token
        val refreshTokenRequest = RefreshTokenRequest(authResponse.refreshToken)
        jsonRequest = jacksonObjectMapper().writeValueAsString(refreshTokenRequest)

        response = mockMvc.perform(
            post("/api/auth/refresh")
              .contentType(MediaType.APPLICATION_JSON)
              .content(jsonRequest)
        )
          .andExpect(status().isOk)
          .andExpect(jsonPath("$.token").isNotEmpty).andReturn().response.contentAsString

        val newAccessToken = jacksonObjectMapper().readValue(response, TokenResponse::class.java)

        reset(tokenService)

        // access secured endpoint with the new token
        mockMvc.perform(
            get("/api/hello")
              .header("Authorization", "Bearer ${newAccessToken.token}")
        )
          .andExpect(status().isOk)
          .andExpect(content().string("Hello, Authorized User!"))
    }

    @Test
    fun `refresh token with invalid refresh token should return unauthorized`() {
        val jsonRequest = jacksonObjectMapper().writeValueAsString(
            RefreshTokenRequest(
                expiredToken
            )
        )

        mockMvc.perform(
            post("/api/auth/refresh")
              .contentType(MediaType.APPLICATION_JSON)
              .content(jsonRequest)
        )
          .andExpect(status().isUnauthorized)
    }

    @Test
    fun `should return unauthorized for unauthenticated user`() {
        val authRequest = AuthenticationRequest("some-user", "pass1")
        val jsonRequest = jacksonObjectMapper().writeValueAsString(authRequest)

        mockMvc.perform(
            post("/api/auth")
              .contentType(MediaType.APPLICATION_JSON)
              .content(jsonRequest)
        )
          .andExpect(status().isUnauthorized)
    }

    @Test
    fun `should return forbidden for tampered refresh token`() {
        val authRequest = AuthenticationRequest("email-1@gmail.com", "pass1")
        var jsonRequest = jacksonObjectMapper().writeValueAsString(authRequest)

        val response = mockMvc.perform(
            post("/api/auth")
              .contentType(MediaType.APPLICATION_JSON)
              .content(jsonRequest)
        )
          .andExpect(status().isOk)
          .andExpect(jsonPath("$.accessToken").isNotEmpty)
          .andExpect(jsonPath("$.refreshToken").isNotEmpty).andReturn().response.contentAsString

        val authResponse = jacksonObjectMapper().readValue(response, AuthenticationResponse::class.java)

        val refreshTokenRequest = RefreshTokenRequest(authResponse.refreshToken)
        jsonRequest = jacksonObjectMapper().writeValueAsString(refreshTokenRequest)

        `when`(userDetailsService.loadUserByUsername("email-1@gmail.com"))
          .thenReturn(User("email-2@gmail.com", "pass2", ArrayList()))

        mockMvc.perform(
            post("/api/auth/refresh")
              .contentType(MediaType.APPLICATION_JSON)
              .content(jsonRequest)
        )
          .andExpect(status().isUnauthorized)
    }

    @Test
    fun `should return forbidden for tampered token`() {
        val authRequest = AuthenticationRequest("email-1@gmail.com", "pass1")
        val jsonRequest = jacksonObjectMapper().writeValueAsString(authRequest)

        val response = mockMvc.perform(
            post("/api/auth")
              .contentType(MediaType.APPLICATION_JSON)
              .content(jsonRequest)
        )
          .andExpect(status().isOk)
          .andExpect(jsonPath("$.accessToken").isNotEmpty)
          .andExpect(jsonPath("$.refreshToken").isNotEmpty).andReturn().response.contentAsString

        val authResponse = jacksonObjectMapper().readValue(response, AuthenticationResponse::class.java)

        `when`(userDetailsService.loadUserByUsername("email-1@gmail.com"))
          .thenReturn(User("email-2@gmail.com", "pass2", ArrayList()))

        mockMvc.perform(
            get("/api/hello")
              .header("Authorization", "Bearer ${authResponse.accessToken}")
        )
          .andExpect(status().isForbidden)
    }
}

