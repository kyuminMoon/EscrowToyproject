package com.tistory.kmmoon.auth.adaptor.`in`

import com.tistory.kmmoon.auth.application.port.`in`.AuthLoginUseCase
import com.tistory.kmmoon.auth.application.port.`in`.AuthSignupUseCase
import com.tistory.kmmoon.auth.domain.request.AuthLoginRequest
import com.tistory.kmmoon.auth.domain.response.AuthLoginResponse
import com.tistory.kmmoon.core.security.JwtFilter
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthController {

  lateinit var authLoginUseCase: AuthLoginUseCase
  lateinit var authSignupUseCase: AuthSignupUseCase

  @GetMapping("/login")
  fun authorize(@Valid @RequestBody loginDto: AuthLoginRequest): ResponseEntity<AuthLoginResponse> {
    val token: AuthLoginResponse = authLoginUseCase.login(loginDto.email, loginDto.password)

    // response header 에도 넣고 응답 객체에도 넣는다.
    val httpHeaders = HttpHeaders()
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.accessToken)
    return ResponseEntity(token, httpHeaders, HttpStatus.OK)
  }

  @PostMapping("/signup")
  fun signup(@RequestBody request: UserCreateRequest): ResponseEntity<AuthLoginResponse> {
    return ResponseEntity
      .ok()
      .body(authSignupUseCase.signUp(request))
  }
}