package com.tistory.kmmoon.core.config

import com.tistory.kmmoon.core.security.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtAuthFilter(
    private val jpaUserDetailsService: CustomUserDetailsService,
    private val jwtUtils: JwtUtils
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        val userEmail: String?
        val jwtToken: String? = null
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response)
            return
        }

//        for (Cookie cookie : request.getCookies()) {
//            if (cookie.getName().equals("jwt")) {
//                jwtToken = cookie.getValue();
//                System.out.println(cookie.getValue());
//            }
//        }
        if (jwtToken == null) {
            filterChain.doFilter(request, response)
            return
        }

//        jwtToken = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwtToken)
        if (userEmail != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = jpaUserDetailsService.loadUserByUsername(userEmail)
            if (jwtUtils.validateToken(jwtToken, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, userDetails.authorities
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }
}
