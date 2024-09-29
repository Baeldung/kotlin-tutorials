package com.baeldung.security.jwt.filter

import com.baeldung.security.jwt.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthorizationFilter(
    private val userDetailsService: UserDetailsService,
    private val tokenService: TokenService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader("Authorization")

        if (null != authorizationHeader && authorizationHeader.startsWith("Bearer ")) {
            try {
                val token: String = authorizationHeader.substringAfter("Bearer ")
                val username: String = tokenService.extractUsername(token)

                if (SecurityContextHolder.getContext().authentication == null) {
                    val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)

                    if (username == userDetails.username) {
                        val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                }
            } catch (ex: Exception) {
                response.writer.write("""{"error": "Filter Authorization error: ${ex.message ?: "unknown error"}"}""")
            }
        }

        filterChain.doFilter(request, response)
    }
}