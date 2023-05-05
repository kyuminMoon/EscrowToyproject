package com.tistory.kmmoon.auth

import com.tistory.kmmoon.auth.application.port.AccountService
import com.tistory.kmmoon.config.DatabaseCleanupBefore
import com.tistory.kmmoon.core.security.JwtTokenProvider
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.dao.DataIntegrityViolationException

@ComponentScan(basePackages = [
  "com.tistory.kmmoon"
])
internal class AuthServiceTest : DatabaseCleanupBefore() {

  @Autowired
  lateinit var accountService: AccountService

  lateinit var tokenProvider: JwtTokenProvider

  var request = UserCreateRequest(
    email = "email@email.com",
    password = "password",
    name = "name",
    phone = "010-0000-0000"
  )

  @Test
  fun 회원가입() {
    val tokenInfo = accountService.signUp(request)
    val accessTokenIsValid = tokenProvider.validateToken(tokenInfo?.accessToken)
    val refreshTokenIsValid = tokenProvider.validateToken(tokenInfo?.refreshToken)
    assertAll(
      { assertThat(accessTokenIsValid).isTrue() }, //
      { assertThat(refreshTokenIsValid).isTrue() }, //
    )
  }

  @Test
  fun 회원가입_Email_UK() {
    try {
      accountService.signUp(request)
      accountService.signUp(request)
    } catch (e : DataIntegrityViolationException) {
      println(e.message)
    }

  }
}