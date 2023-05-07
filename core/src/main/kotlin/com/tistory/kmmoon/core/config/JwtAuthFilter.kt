package com.tistory.kmmoon.core.config

import com.tistory.kmmoon.core.security.JwtTokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtAuthFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authorizationHeader: String = request.getHeader("Authorization") ?: return filterChain.doFilter(request, response)

            val token = authorizationHeader.substring("Bearer ".length)

            if (jwtTokenProvider.validateAccessToken(token)) {
                val authentication: Authentication = jwtTokenProvider.getAuthentication(token)

                SecurityContextHolder.getContext().authentication = authentication
            }

        } catch (e: Exception) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpStatus.FORBIDDEN.value(), "인증에 실패했습니다.");
            e.printStackTrace()
            return
        }

        filterChain.doFilter(request, response)
    }
}
