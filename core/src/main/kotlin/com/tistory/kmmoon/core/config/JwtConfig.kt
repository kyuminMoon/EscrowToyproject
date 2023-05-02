package com.tistory.kmmoon.core.config

import com.tistory.kmmoon.core.security.TokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig {
  @Value("\${jwt.secret}")
  lateinit var accessTokenSecret: String

  @Value("\${jwt.access-token-validity-in-seconds}")
  var accessTokenValidityInSeconds: Long = 0

  @Value("\${jwt.refresh-token-validity-in-seconds}")
  var refreshTokenValidityInSeconds: Long = 0

  // 액세스 토큰 발급용, 리프레시 토큰 발급용은 각각 별도의 키와 유효기간을 갖는다.
  @Bean(name = ["tokenProvider"])
  fun tokenProvider(): TokenProvider {
    return TokenProvider(accessTokenSecret, accessTokenValidityInSeconds, refreshTokenValidityInSeconds)
  }
}