package com.tistory.kmmoon.core.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException

class JwtFilter(private val tokenProvider: TokenProvider) : GenericFilterBean() {
  // 실제 필터링 로직은 doFilter 안에 들어가게 된다. GenericFilterBean을 받아 구현
  // Dofilter는 토큰의 인증정보를 SecurityContext 안에 저장하는 역할 수행
  // 현재는 jwtFilter 통과 시 loadUserByUsername을 호출하여 디비를 거치지 않으므로 시큐리티 컨텍스트에는 엔티티 정보를 온전히 가지지 않는다
  // 즉 loadUserByUsername을 호출하는 인증 API를 제외하고는 유저네임, 권한만 가지고 있으므로 Account 정보가 필요하다면 디비에서 꺼내와야함
  @Throws(IOException::class, ServletException::class)
  override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
    val httpServletRequest = servletRequest as HttpServletRequest
    val jwt = resolveToken(httpServletRequest)
    val requestURI = httpServletRequest.requestURI

    // 유효성 검증
    if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
      // 토큰에서 유저네임, 권한을 뽑아 스프링 시큐리티 유저를 만들어 Authentication 반환
      val authentication = tokenProvider.getAuthentication(jwt)
      // 해당 스프링 시큐리티 유저를 시큐리티 건텍스트에 저장, 즉 디비를 거치지 않음
      SecurityContextHolder.getContext().authentication = authentication
      Companion.logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.name, requestURI)
    } else {
      Companion.logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI)
    }
    filterChain.doFilter(servletRequest, servletResponse)
  }

  // 헤더에서 토큰 정보를 꺼내온다.
  private fun resolveToken(request: HttpServletRequest): String? {
    val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
    return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      bearerToken.substring(7)
    } else null
  }

  companion object {
    private val logger = LoggerFactory.getLogger(JwtFilter::class.java)
    const val AUTHORIZATION_HEADER = "Authorization"
  }
}