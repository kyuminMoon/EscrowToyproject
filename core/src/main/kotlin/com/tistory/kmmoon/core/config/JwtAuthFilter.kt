package com.tistory.kmmoon.core.config

import com.tistory.kmmoon.core.security.CustomUserDetailsService
import com.tistory.kmmoon.core.security.JwtTokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtAuthFilter(
    private val jpaUserDetailsService: CustomUserDetailsService,
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        // 헤더에 Authorization이 있다면 가져온다.
        val authorizationHeader: String = request.getHeader("Authorization") ?: return filterChain.doFilter(request, response)
        // Bearer타입 토큰이 있을 때 가져온다.
        val token = authorizationHeader.substring("Bearer ".length)

        // 토큰 검증
        if (jwtTokenProvider.validateAccessToken(token)) {
            // token으로 AuthenticationToken 생성
            val authentication: Authentication = jwtTokenProvider.getAuthentication(token)
//            jpaUserDetailsService.loadUserByUsername(authentication.name).username ?: return filterChain.doFilter(request, response)

            // 생성된 AuthenticationToken을 SecurityContext가 관리하도록 설정
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}
