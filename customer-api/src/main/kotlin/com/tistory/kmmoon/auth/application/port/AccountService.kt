package com.tistory.kmmoon.auth.application.port

import com.tistory.kmmoon.auth.application.port.`in`.AuthLoginUseCase
import com.tistory.kmmoon.auth.application.port.`in`.AuthSignupUseCase
import com.tistory.kmmoon.auth.application.port.out.AuthLoginPort
import com.tistory.kmmoon.auth.application.port.out.AuthSignupPort
import com.tistory.kmmoon.auth.domain.response.AuthLoginResponse
import com.tistory.kmmoon.core.exception.ErrorMessage
import com.tistory.kmmoon.core.exception.UserGuideException
import com.tistory.kmmoon.core.security.TokenProvider
import com.tistory.kmmoon.user.Authority
import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.UserRole
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(

  private val tokenProvider: TokenProvider,

  private val authenticationManagerBuilder: AuthenticationManagerBuilder,

  @Autowired
  private val signupPort: AuthSignupPort,

  @Autowired
  private val loginPort: AuthLoginPort,

  @Autowired
  private val passwordEncoder: PasswordEncoder
) : AuthLoginUseCase, AuthSignupUseCase {

  // username 과 패스워드로 사용자를 인증하여 액세스토큰을 반환한다.
  @Throws(UserGuideException::class)
  override fun login(email: String, password: String): AuthLoginResponse {
    // 유저 로그인 로직 추가 email + password
    val isSuccess = loginPort.login(email, password) != null

    if(isSuccess)
      throw UserGuideException(ErrorMessage.LOGIN_FAILED)

    return authLoginResponse(email, password)
  }

  @Throws(UserGuideException::class)
  override fun signUp(request: UserCreateRequest): AuthLoginResponse? {
    // 유저 검증 로직 추가
    val existsEmail = signupPort.existsEmail(request.email)

    if(existsEmail)
      throw UserGuideException(ErrorMessage.ALREADY_IN_USE)

    val encodePassword = passwordEncoder.encode(request.password)

    // 회원가입 로직 추가
    val authorities = listOf(Authority(authorityName = UserRole.ROLE_USER)).toSet()

    val userEntity = UserEntity(
      email = request.email,
      password = encodePassword,
      name = request.name,
      phone = request.phone,
      authorities = authorities,
    )

    signupPort.signup(userEntity)

    return authLoginResponse(request.email, encodePassword)
  }


  private fun authLoginResponse(email: String, password: String): AuthLoginResponse {
    // 받아온 유저네임과 패스워드를 이용해 UsernamePasswordAuthenticationToken 객체 생성
    val authenticationToken = UsernamePasswordAuthenticationToken(email, password)

    // authenticationToken 객체를 통해 Authentication 객체 생성
    // 이 과정에서 CustomUserDetailsService 에서 우리가 재정의한 loadUserByUsername 메서드 호출
    val authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)
    // 그 객체를 시큐리티 컨텍스트에 저장
    SecurityContextHolder.getContext().authentication = authentication

    // 인증 정보를 기준으로 jwt access 토큰 생성
    val accessToken = tokenProvider.createAccessToken(authentication)
    val refreshToken = tokenProvider.createRefreshToken(authentication)
    return AuthLoginResponse(
      accessToken = accessToken,
      refreshToken = refreshToken
    )
  }


}